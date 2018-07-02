package com.opendat.TestNewFunc;

import com.opendat.dao.SQL.CompanyArchivedDAO;
import com.opendat.dao.SQL.CompanyDAO;
import com.opendat.dao.XML.CompanyDb.CompanyXmlReader;
import com.opendat.dao.XML.CompanyDb.CompanyXmlReaderImpl;
import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.CompanyDatabaseStructureInfo;
import com.opendat.model.NoSql.CompanyHash;
import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.NoSql.SettingsRecords.CompanyRecordRestrictions;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.service.CompanyService;
import com.opendat.service.LogEventService;
import com.opendat.util.CompanyServiceTools.ParseXml.ArchivedCompanyServiceTools;
import com.opendat.util.CompanyServiceTools.ParseXml.CompanyServiceTools;
import com.opendat.util.CompanyServiceTools.ParseXml.ParseXmlCompanyServiceTools;
import com.opendat.util.Validators.XmlCompanyRecordValidator;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TestNewFuncService {

    @Autowired
    public CompanyDAO companyDAO;
    @Autowired
    public CompanyArchivedDAO companyArchivedDAO;
    @Autowired
    public LogEventService logeventservice;
    @Autowired
    public CompanyServiceTools companyServiceTools;
    @Autowired
    public CompanyService companyService;
    @Autowired
    public ArchivedCompanyServiceTools archivedCompanyServiceTools;
    @Autowired
    public ParseXmlCompanyServiceTools parseXmlCompanyServiceTools;
    @Autowired
    public Tools tools;

//    private CompanyUpdateStatistic ResolveArchiveCompaniesUpdateList(List<CompanyArchived> candidToArchiveToDbListOfCompanies, CompanyUpdateStatistic companyUpdateStatistic) {
//        List<CompanyArchived> updateToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
//        List<CompanyArchived> requestedForComparationCompanyList;
//        List<CompanyArchived> identiqueRegardlessDatesCompanyList;
//        CompanyArchived companyToBeUpdated;
//        CompanyArchived companyFromEqualList;
//        for (CompanyArchived examinedCandidCompany : candidToArchiveToDbListOfCompanies) {
//            companyFromEqualList = new CompanyArchived();
//            companyToBeUpdated = new CompanyArchived();
//            companyToBeUpdated = examinedCandidCompany;
//            requestedForComparationCompanyList = companyArchivedDAO.list(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
//            identiqueRegardlessDatesCompanyList = archivedCompanyServiceTools.SelectIdentiqueRecordRegardlessDateList(requestedForComparationCompanyList, examinedCandidCompany);
//            if (identiqueRegardlessDatesCompanyList.size() > 0) {
//                identiqueRegardlessDatesCompanyList.sort(Comparator.comparing(CompanyArchived::getDate));
//                companyFromEqualList = identiqueRegardlessDatesCompanyList.get(0);//record with oldest day
//                //do nothing to avoid archive boost
//            }
//            companyUpdateStatistic.setUpdated(companyUpdateStatistic.getUpdated() + 1);
//        }
//        return companyUpdateStatistic;
//    }
//
//
//    private CompanyUpdateStatistic ResolveArchiveCompaniesList(List<CompanyArchived> candidToArchiveToDbListOfCompanies, CompanyUpdateStatistic companyUpdateStatistic) {
//        List<CompanyArchived> insertToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
//        List<CompanyArchived> updateToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
//        List<CompanyArchived> requestedForComparationCompanyList;
//        CompanyArchived companyToBeArchived;
//        for (CompanyArchived examinedCandidCompany : candidToArchiveToDbListOfCompanies) {
//            companyToBeArchived = new CompanyArchived();
//            companyToBeArchived = examinedCandidCompany;
//            requestedForComparationCompanyList = companyArchivedDAO.list(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
//            if (requestedForComparationCompanyList.size() == 0)//no recored in archive of current company
//            {
//                insertToArchiveDbListOfCompanies.add(companyToBeArchived);
//                companyUpdateStatistic.setArchived(companyUpdateStatistic.getArchived() + 1);
//            } else //there are records in archive with same code
//                if (!archivedCompanyServiceTools.ArchiveListContainIdentiqueRecord(examinedCandidCompany, requestedForComparationCompanyList))//do nothing is idettique record already exist in archive but add candid to update list if other case
//                {
//                    insertToArchiveDbListOfCompanies.add(companyToBeArchived);
//                    companyUpdateStatistic.setArchived(companyUpdateStatistic.getArchived() + 1);
//                } else//identique record was not found
//
//                    if (!archivedCompanyServiceTools.ArchiveListContainIdentiqueRecordRegardlessDate(examinedCandidCompany, requestedForComparationCompanyList))//existing company records in archive defferent essentially
//                    {
//                        insertToArchiveDbListOfCompanies.add(companyToBeArchived);
//                        companyUpdateStatistic.setArchived(companyUpdateStatistic.getArchived() + 1);
//                    } else //existing company records in archive differs only in dates records
//                        updateToArchiveDbListOfCompanies.add(companyToBeArchived);
//        }
//
//        companyArchivedDAO.addCompaniesArchived(insertToArchiveDbListOfCompanies); //unique records add
//        companyUpdateStatistic = ResolveArchiveCompaniesUpdateList(updateToArchiveDbListOfCompanies, companyUpdateStatistic);//resolve other recored
//        return companyUpdateStatistic;
//    }
//
//
//    private CompanyUpdateStatistic ResolveUpdateCompaniesList(List<Company> companies, CompanyUpdateStatistic companyUpdateStatistic) {
//        int[] compareResult;
//        List<CompanyArchived> insertToArchiveDbListOfCompanies = new LinkedList<CompanyArchived>();
//        List<Company> updateToDbListOfCompanies = new LinkedList<Company>();
//        List<Company> requestedForComparationCompanyList;
//        CompanyArchived candidArchivedCompany;
//        Company existInDbCompany;
//
//
//        for (Company examinedCandidCompany : companies) {
//            compareResult = new int[9];
//            existInDbCompany = new Company();
//            candidArchivedCompany = new CompanyArchived();
//            requestedForComparationCompanyList = companyService.SearchCompanies(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
//            existInDbCompany = requestedForComparationCompanyList.get(0);
//
//            if (examinedCandidCompany.getDate().after(existInDbCompany.getDate()))//record in XML if newer
//            {
//                candidArchivedCompany = companyServiceTools.CompanyToCompanyArchived(existInDbCompany, examinedCandidCompany.getDate());
//                insertToArchiveDbListOfCompanies.add(candidArchivedCompany);
//                existInDbCompany = companyServiceTools.CompanyUpdateRecords(examinedCandidCompany, existInDbCompany, compareResult);
//                updateToDbListOfCompanies.add(existInDbCompany);
//            } else if (examinedCandidCompany.getDate().before(existInDbCompany.getDate()))//record in XML if older
//            {
//                candidArchivedCompany = companyServiceTools.CompanyToCompanyArchived(existInDbCompany, examinedCandidCompany.getDate());
//                insertToArchiveDbListOfCompanies.add(candidArchivedCompany);
//            } else if (examinedCandidCompany.getDate().equals(existInDbCompany.getDate()))//record in XML if equal
//            {
//                candidArchivedCompany = companyServiceTools.CompanyToCompanyArchived(existInDbCompany, examinedCandidCompany.getDate());
//                insertToArchiveDbListOfCompanies.add(candidArchivedCompany);
//                existInDbCompany = companyServiceTools.CompanyUpdateRecords(examinedCandidCompany, existInDbCompany, compareResult);
//                updateToDbListOfCompanies.add(existInDbCompany);
//            }
//        }
//        companyService.UpdateCompaniesService(updateToDbListOfCompanies);
//        companyUpdateStatistic = ResolveArchiveCompaniesList(insertToArchiveDbListOfCompanies, companyUpdateStatistic);
//        return companyUpdateStatistic;
//    }
//
//    private CompanyUpdateStatistic ResolveCompanyListExtractedFromXMLSource(List<Company> companyListFromXml, CompanyUpdateStatistic companyUpdateStatistic) {
//        try {
//            List<Company> insertToDbListOfCompanies = new LinkedList<Company>();
//            List<Company> updateToDbListOfCompanies = new LinkedList<Company>();
//            Company existInDbCompany;
//            List<Company> requestedForComparationCompanyList;
//            for (Company examinedCandidCompany : companyListFromXml
//                    ) {
//                requestedForComparationCompanyList = new LinkedList<Company>();
//                existInDbCompany = new Company();
//                requestedForComparationCompanyList = companyService.SearchCompanies(examinedCandidCompany.getEDRPOU().toString(), "EDRPOU");
//                if (requestedForComparationCompanyList.size() == 1) //company already exist in db
//                {
//                    existInDbCompany = requestedForComparationCompanyList.get(0);
//                    if (!companyServiceTools.ifCompaniesIdentique(existInDbCompany, examinedCandidCompany))//company info in XML file differs from DB info
//                    {
//                        updateToDbListOfCompanies.add(examinedCandidCompany);
//                        companyUpdateStatistic.setUpdated(companyUpdateStatistic.getUpdated() + 1);
//                    }
//                    // else do nothing becouse records are the same
//                } else if (requestedForComparationCompanyList.size() == 0) //company is new, just add to current db
//                {
//                    insertToDbListOfCompanies.add(examinedCandidCompany);
//                    companyUpdateStatistic.setAdded(companyUpdateStatistic.getAdded() + 1);
//
//                } else if (requestedForComparationCompanyList.size() > 1) //company code should be unic in current (actual) DB
//                {
//                    logeventservice.message2log("Non unic edrpou " + examinedCandidCompany.getEDRPOU().toString());
//                    throw new MyException("Non unic edrpou " + examinedCandidCompany.getEDRPOU().toString());
//                }
//            }
//
//            //inserting new records to current DB
//            companyService.AddCompanies(insertToDbListOfCompanies);
//            companyUpdateStatistic = ResolveUpdateCompaniesList(updateToDbListOfCompanies, companyUpdateStatistic);
//
//        } catch (Exception ex) {
//            logeventservice.message2log(ex.getMessage());
//        }
//        return companyUpdateStatistic;
//    }
//
//
//    public List<Company> GetNewRecordsFromXml(String filename, Date date, Integer amount) {
//        List<Company> companyList = new ArrayList<Company>();
//        List<Company> existCompanyList = new ArrayList<Company>();
//
//        List<CompanyHash> ExistCompanyHashList = companyDAO.getHashMap();
//        int size = ExistCompanyHashList.size();
//
//        int[] companyHashArray = new int[size];
//        int[] companyIdArray = new int[size];
//        int[] companyEDRPOUArray = new int[size];
//
//        int position = 0;
//        for (CompanyHash c : ExistCompanyHashList
//                ) {
//            int id = c.getId();
//            int hashcode = c.getHashCode();
//            int edrpou = c.getEDRPOU();
//
//            companyIdArray[position] = id;
//            companyHashArray[position] = hashcode;
//            companyEDRPOUArray[position] = edrpou;
//
//            position++;
//        }
//
//        //    Arrays.sort(companyHashArray, (a, b) -> Long.compare(a[0], b[0]));
//
//        CompanyXmlReaderImpl cXmlR = new CompanyXmlReaderImpl();
//        cXmlR.Open(filename, date);
//        int recordsCount = 200_000;//cXmlR.countRecords();
//        for (int i = 0; i < recordsCount; i++) {
//            int IdOfCompany = 0;
//            int IdOfHash = 0;
//            CompanyRecordReadResult company = cXmlR.readNextRecord();
//            IdOfHash = ArrayUtils.indexOf(companyHashArray, company.getCompany().getHashCode());
//            if (IdOfHash == -1) {
//                companyList.add(company.getCompany());
//                IdOfCompany = ArrayUtils.indexOf(companyEDRPOUArray, Integer.parseInt(company.getCompany().getEDRPOU()));
//                if (IdOfCompany > -1) {
//                    company.setCompany(companyDAO.getCompanyById(companyIdArray[IdOfCompany]));
//                    companyList.add(company.getCompany());
//                }
//            }
//        }
//        return companyList;
//    }
//
//
//    public ListOfUnicLegalForms SmartParseXml(String filename, Date date, Integer amount) {
//        ListOfUnicLegalForms listOfUnicLegalForms = new ListOfUnicLegalForms();
//        CompanyDataParser companyDataParser = new CompanyDataParser();
//        CompanyXmlReaderImpl cXmlR = new CompanyXmlReaderImpl();
//        cXmlR.Open(filename, date);
//        int recordsCount = 1_000_000;//cXmlR.countRecords();
//        for (int i = 0; i < recordsCount; i++) {
//            CompanyRecordReadResult company = cXmlR.readNextRecord();
//            if (company==null)
//                return listOfUnicLegalForms;
//            String [] result = companyDataParser.parseName(company.getCompany().getNAME());
//            if (!listOfUnicLegalForms.IfHasValue(result[0]))
//                listOfUnicLegalForms.AddKey(result[0]);
//            else listOfUnicLegalForms.incrementValueByKey(result[0]);
//        }
//        return listOfUnicLegalForms;
//    }
//
//
//    public List<Company> SetHashCodes() {
//        List<Company> companies;
//        int count = 0;
//        companies = companyDAO.list(0, 100);
//        for (int i = 0; i < 100; i++) {
//            Company co = companies.get(i);
//            co.setHashCode(co.hashCode());
//            companies.add(co);
//        }
//        logeventservice.message2log("hashSet 100" + count);
//
//        return companies;
//    }
//
//
    public List<Company> analyzeXmlFile(String filename, Date date, Integer amount) {
        CompanyXmlReader companyXmlReader = new CompanyXmlReaderImpl();
        CompanyRecordReadResult companyRecordReadResult;
        companyXmlReader.Open(filename, date);
        List<Company>companies=new ArrayList<>();
        long countRecords = companyXmlReader.countRecords();
        companyXmlReader = new CompanyXmlReaderImpl();
        companyXmlReader.Open(filename, date);
        XmlCompanyRecordValidator xmlCompanyRecordValidator;
        CompanyRecordRestrictions companyRecordRestrictions =new CompanyRecordRestrictions();
        for (int i = 0; i < countRecords; i++) {
            companyRecordReadResult=companyXmlReader.readNextRecord();
            xmlCompanyRecordValidator=new XmlCompanyRecordValidator();
            if (!xmlCompanyRecordValidator.isValid(companyRecordReadResult,companyRecordRestrictions))
                companies.add(companyRecordReadResult.getCompany());
        }
        return companies;
    }

    public CompanyDatabaseStructureInfo parseXML (String filename, Date date){
        CompanyDatabaseStructureInfo companyDatabaseStructureInfo = new CompanyDatabaseStructureInfo (date,filename);
        companyDatabaseStructureInfo = parseXmlCompanyServiceTools.createCompanyDb( filename,  date);
        return companyDatabaseStructureInfo;
    }



}
