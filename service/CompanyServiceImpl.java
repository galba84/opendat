/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;


import com.opendat.dao.XML.CompanyXmlReaderImpl;
import com.opendat.dao.SQL.CompanyArchivedDAO;
import com.opendat.dao.SQL.CompanyDAO;
import com.opendat.dao.SQL.FounderDAO;
import com.opendat.Exceptions.MyException;
import com.opendat.model.NoSql.CompanyHash;
import com.opendat.model.NoSql.CompanyStatistic;
import com.opendat.model.NoSql.CompanyUpdateStatistic;
import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.SqlDb.Company.CompanyArchived;
import com.opendat.model.NoSql.NaturanLanguageProcessing.ListOfUnicLegalForms;
import com.opendat.util.CompanyServiceTools.ParseXml.ArchivedCompanyServiceTools;
import com.opendat.util.CompanyServiceTools.ParseXml.CompanyServiceTools;
import com.opendat.util.CompanyServiceTools.ParseXml.ParseXmlCompanyServiceTools;
import com.opendat.util.NaturalLanguageProcessing.CompanyDataParser;
import com.opendat.util.Tools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service("companyService")
@Transactional

public class CompanyServiceImpl implements CompanyService {


    @Autowired
    public CompanyArchivedDAO companyArchivedDAO;
    @Autowired
    public CompanyDAO companyDAO;
    @Autowired
    public FounderDAO founderDAO;
    @Autowired
    public LogEventService logeventservice;
    @Autowired
    public CompanyServiceTools companyServiceTools;
    @Autowired
    public ArchivedCompanyServiceTools archivedCompanyServiceTools;
    @Autowired
    public ParseXmlCompanyServiceTools parseXmlCompanyServiceTools;
    @Autowired
    public Tools tools;


    @Override
    @Transactional
    public void UpdateCompanyService(Company company) {
        companyDAO.updateCompany(company);

    }

    @Override
    @Transactional
    public void UpdateCompaniesService(List<Company> companies) {
        companyDAO.updateCompanies(companies);
    }

    private CompanyUpdateStatistic ResolveArchiveCompaniesUpdateList(List<CompanyArchived> candidToArchiveToDbListOfCompanies, CompanyUpdateStatistic companyUpdateStatistic) {
        List<CompanyArchived> updateToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
        List<CompanyArchived> requestedForComparationCompanyList;
        List<CompanyArchived> identiqueRegardlessDatesCompanyList;
        CompanyArchived companyToBeUpdated;
        CompanyArchived companyFromEqualList;
        for (CompanyArchived examinedCandidCompany : candidToArchiveToDbListOfCompanies) {
            companyFromEqualList = new CompanyArchived();
            companyToBeUpdated = new CompanyArchived();
            companyToBeUpdated = examinedCandidCompany;
            requestedForComparationCompanyList = companyArchivedDAO.list(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
            identiqueRegardlessDatesCompanyList = archivedCompanyServiceTools.SelectIdentiqueRecordRegardlessDateList(requestedForComparationCompanyList, examinedCandidCompany);
            if (identiqueRegardlessDatesCompanyList.size() > 0) {
                identiqueRegardlessDatesCompanyList.sort(Comparator.comparing(CompanyArchived::getDate));
                companyFromEqualList = identiqueRegardlessDatesCompanyList.get(0);//record with oldest day
                //do nothing to avoid archive boost
            }

            companyUpdateStatistic.setUpdated(companyUpdateStatistic.getUpdated() + 1);
        }
        return companyUpdateStatistic;
    }


    private CompanyUpdateStatistic ResolveArchiveCompaniesList(List<CompanyArchived> candidToArchiveToDbListOfCompanies, CompanyUpdateStatistic companyUpdateStatistic) {
        List<CompanyArchived> insertToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
        List<CompanyArchived> updateToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
        List<CompanyArchived> requestedForComparationCompanyList;
        CompanyArchived companyToBeArchived;
        for (CompanyArchived examinedCandidCompany : candidToArchiveToDbListOfCompanies) {
            companyToBeArchived = new CompanyArchived();
            companyToBeArchived = examinedCandidCompany;
            requestedForComparationCompanyList = companyArchivedDAO.list(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
            if (requestedForComparationCompanyList.size() == 0)//no recored in archive of current company
            {
                insertToArchiveDbListOfCompanies.add(companyToBeArchived);
                companyUpdateStatistic.setArchived(companyUpdateStatistic.getArchived() + 1);
            } else //there are records in archive with same code
                if (!archivedCompanyServiceTools.ArchiveListContainIdentiqueRecord(examinedCandidCompany, requestedForComparationCompanyList))//do nothing is idettique record already exist in archive but add candid to update list if other case
                {
                    insertToArchiveDbListOfCompanies.add(companyToBeArchived);
                    companyUpdateStatistic.setArchived(companyUpdateStatistic.getArchived() + 1);
                } else//identique record was not found

                    if (!archivedCompanyServiceTools.ArchiveListContainIdentiqueRecordRegardlessDate(examinedCandidCompany, requestedForComparationCompanyList))//existing company records in archive defferent essentially
                    {
                        insertToArchiveDbListOfCompanies.add(companyToBeArchived);
                        companyUpdateStatistic.setArchived(companyUpdateStatistic.getArchived() + 1);
                    } else //existing company records in archive differs only in dates records
                        updateToArchiveDbListOfCompanies.add(companyToBeArchived);
        }

        companyArchivedDAO.addCompaniesArchived(insertToArchiveDbListOfCompanies); //unique records add
        companyUpdateStatistic = ResolveArchiveCompaniesUpdateList(updateToArchiveDbListOfCompanies, companyUpdateStatistic);//resolve other recored
        return companyUpdateStatistic;
    }

    private CompanyUpdateStatistic ResolveUpdateCompaniesList(List<Company> companies, CompanyUpdateStatistic companyUpdateStatistic) {
        int[] compareResult;
        List<CompanyArchived> insertToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
        List<Company> updateToDbListOfCompanies = new LinkedList<Company>();
        List<Company> requestedForComparationCompanyList;
        CompanyArchived candidArchivedCompany;
        Company existInDbCompany;


        for (Company examinedCandidCompany : companies) {
            compareResult = new int[9];
            existInDbCompany = new Company();
            candidArchivedCompany = new CompanyArchived();
            requestedForComparationCompanyList = this.SearchCompanies(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
            existInDbCompany = requestedForComparationCompanyList.get(0);

            if (examinedCandidCompany.getDate().after(existInDbCompany.getDate()))//record in XML if newer
            {
                candidArchivedCompany = companyServiceTools.CompanyToCompanyArchived(existInDbCompany, examinedCandidCompany.getDate());
                insertToArchiveDbListOfCompanies.add(candidArchivedCompany);
                existInDbCompany = companyServiceTools.CompanyUpdateRecords(examinedCandidCompany, existInDbCompany, compareResult);
                updateToDbListOfCompanies.add(existInDbCompany);
            } else if (examinedCandidCompany.getDate().before(existInDbCompany.getDate()))//record in XML if older
            {
                candidArchivedCompany = companyServiceTools.CompanyToCompanyArchived(existInDbCompany, examinedCandidCompany.getDate());
                insertToArchiveDbListOfCompanies.add(candidArchivedCompany);
            } else if (examinedCandidCompany.getDate().equals(existInDbCompany.getDate()))//record in XML if equal
            {
                candidArchivedCompany = companyServiceTools.CompanyToCompanyArchived(existInDbCompany, examinedCandidCompany.getDate());
                insertToArchiveDbListOfCompanies.add(candidArchivedCompany);
                existInDbCompany = companyServiceTools.CompanyUpdateRecords(examinedCandidCompany, existInDbCompany, compareResult);
                updateToDbListOfCompanies.add(existInDbCompany);
            }
        }
        UpdateCompaniesService(updateToDbListOfCompanies);
        companyUpdateStatistic = ResolveArchiveCompaniesList(insertToArchiveDbListOfCompanies, companyUpdateStatistic);
        return companyUpdateStatistic;
    }

    @Override
    @Transactional
    public void AddCompany(Company company) {
        companyDAO.add(company);
    }

    @Override
    @Transactional
    public void AddCompanies(List<Company> companies) {
        companyDAO.addCompanies(companies);
    }


    private CompanyUpdateStatistic ResolveCompanyListExtractedFromXMLSource(List<Company> companyListFromXml, CompanyUpdateStatistic companyUpdateStatistic) {
        try {
            List<Company> insertToDbListOfCompanies = new LinkedList<Company>();
            List<Company> updateToDbListOfCompanies = new LinkedList<Company>();
            Company existInDbCompany;
            List<Company> requestedForComparationCompanyList;
            for (Company examinedCandidCompany : companyListFromXml
                    ) {
                requestedForComparationCompanyList = new LinkedList<Company>();
                existInDbCompany = new Company();
                requestedForComparationCompanyList = this.SearchCompanies(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
                if (requestedForComparationCompanyList.size() == 1) //company already exist in db
                {
                    existInDbCompany = requestedForComparationCompanyList.get(0);
                    if (!companyServiceTools.ifCompaniesIdentique(existInDbCompany, examinedCandidCompany))//company info in XML file differs from DB info
                    {
                        updateToDbListOfCompanies.add(examinedCandidCompany);
                        companyUpdateStatistic.setUpdated(companyUpdateStatistic.getUpdated() + 1);
                    }
                    // else do nothing becouse records are the same
                } else if (requestedForComparationCompanyList.size() == 0) //company is new, just add to current db
                {
                    insertToDbListOfCompanies.add(examinedCandidCompany);
                    companyUpdateStatistic.setAdded(companyUpdateStatistic.getAdded() + 1);

                } else if (requestedForComparationCompanyList.size() > 1) //company code should be unic in current (actual) DB
                {
                    logeventservice.message2log("Non unic edrpou " + examinedCandidCompany.getEDRPOU().toString());
                    throw new MyException("Non unic edrpou " + examinedCandidCompany.getEDRPOU().toString());
                }
            }

            //inserting new records to current DB
            AddCompanies(insertToDbListOfCompanies);
            companyUpdateStatistic = ResolveUpdateCompaniesList(updateToDbListOfCompanies, companyUpdateStatistic);

        } catch (Exception ex) {
            logeventservice.message2log(ex.getMessage());
        }
        return companyUpdateStatistic;
    }

    @Override
    @Transactional(readOnly = true)
    public long Count() {
        return companyDAO.count();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Company> SearchCompanies(String pattern, String column) {
        return companyDAO.list(pattern, column);
    }


    @Override
    @Transactional
    public List<Company> GetNewRecordsFromXml(String filename, Date date, Integer amount) {
        List<Company> companyList = new ArrayList<Company>();
        List<Company> existCompanyList = new ArrayList<Company>();

        List<CompanyHash> ExistCompanyHashList = companyDAO.getHashMap();
        int size = ExistCompanyHashList.size();

        int[] companyHashArray = new int[size];
        int[] companyIdArray = new int[size];
        int[] companyEDRPOUArray = new int[size];

        int position = 0;
        for (CompanyHash c : ExistCompanyHashList
                ) {
            int id = c.getId();
            int hashcode = c.getHashCode();
            int edrpou = c.getEDRPOU();

            companyIdArray[position] = id;
            companyHashArray[position] = hashcode;
            companyEDRPOUArray[position] = edrpou;

            position++;
        }

        //    Arrays.sort(companyHashArray, (a, b) -> Long.compare(a[0], b[0]));

        CompanyXmlReaderImpl cXmlR = new CompanyXmlReaderImpl();
        cXmlR.Open(filename, date);
        int recordsCount = 200_000;//cXmlR.countRecords();
        for (int i = 0; i < recordsCount; i++) {
            int IdOfCompany = 0;
            int IdOfHash = 0;
            CompanyRecordReadResult company = cXmlR.readNextRecord();
            IdOfHash = ArrayUtils.indexOf(companyHashArray, company.getCompany().getHashCode());
            if (IdOfHash == -1) {
                companyList.add(company.getCompany());
                IdOfCompany = ArrayUtils.indexOf(companyEDRPOUArray, Integer.parseInt(company.getCompany().getEDRPOU()));
                if (IdOfCompany > -1) {
                    company.setCompany(companyDAO.getCompanyById(companyIdArray[IdOfCompany]));
                    companyList.add(company.getCompany());
                }
            }
        }
        return companyList;
    }

    @Override
    @Transactional
    public ListOfUnicLegalForms SmartParseXml(String filename, Date date, Integer amount) {
        ListOfUnicLegalForms listOfUnicLegalForms = new ListOfUnicLegalForms();
        CompanyDataParser companyDataParser = new CompanyDataParser();
        CompanyXmlReaderImpl cXmlR = new CompanyXmlReaderImpl();
        cXmlR.Open(filename, date);
        int recordsCount = 1_000_000;//cXmlR.countRecords();
        for (int i = 0; i < recordsCount; i++) {
            CompanyRecordReadResult company = cXmlR.readNextRecord();
            if (company==null)
                return listOfUnicLegalForms;
            String [] result = companyDataParser.parseName(company.getCompany().getNAME());
            if (!listOfUnicLegalForms.IfHasValue(result[0]))
                listOfUnicLegalForms.AddKey(result[0]);
            else listOfUnicLegalForms.incrementValueByKey(result[0]);
        }
        return listOfUnicLegalForms;
    }



    @Override
    public List<Company> SetHashCodes() {
        List<Company> companies;
        int count = 0;
        companies = companyDAO.list(0, 100);
        for (int i = 0; i < 100; i++) {
            Company co = companies.get(i);
            co.setHashCode(co.hashCode());
            companies.add(co);
        }
        logeventservice.message2log("hashSet 100" + count);

        return companies;
    }

    @Override
    public CompanyStatistic Statistic() {
        LocalDateTime now1 = LocalDateTime.now();
        CompanyStatistic cs = new CompanyStatistic(companyDAO.count());
        cs.setUnicNames(companyDAO.notEmptyColumn("NAME"));
        cs.setUnicShortNames(companyDAO.notEmptyColumn("SHORT_NAME"));
        cs.setUnicEDRPOU(companyDAO.notEmptyColumn("EDRPOU"));
        cs.setUnicADDRESS(companyDAO.notEmptyColumn("ADDRESS"));
        cs.setUnicBOSS(companyDAO.notEmptyColumn("BOSS"));
        cs.setUnicKVED(companyDAO.notEmptyColumn("KVED"));
        cs.setUnicSTAN(companyDAO.notEmptyColumn("STAN"));
        cs.setUnicFOUNDERS(companyDAO.notEmptyColumn("FOUNDERS"));
        cs.setUnicNames(companyDAO.notEmptyColumn("date"));
        cs.setDateTime(now1);
        return cs;
    }


}
