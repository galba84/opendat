/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util.CompanyServiceTools.ParseXml;

import com.opendat.dao.SQL.CompanyDAO;
import com.opendat.dao.XML.CompanyDb.CompanyXmlReader;
import com.opendat.dao.XML.CompanyDb.CompanyXmlReaderImpl;
import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.CompanyDatabaseStructureInfo;
import com.opendat.model.NoSql.CompanyHash;
import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.SqlDb.Log.LogEvent;
import com.opendat.service.LogEventService;
import com.opendat.util.DataStructuresOperators.CompanyDatabaseStructureInfoOperator;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service

public class ParseXmlCompanyServiceTools {
    @Autowired
    CompanyDAO companyDAO;
    @Autowired
    Tools tools;
    @Autowired
    LogEventService logEventService;

    public int countRecords(String filename, Date date) {
        CompanyXmlReaderImpl companyXmlReader = new CompanyXmlReaderImpl();
        companyXmlReader.Open(filename, date);
        int res = (int) companyXmlReader.countRecords();
        companyXmlReader.CloseReader();
        return res;
    }

    //    public void  updateCompanyDb(String filename, Date date, List<CompanyHash> companiesHash) {
//        CompanyDatabaseStructureInfo companyDatabaseStructureInfo = new CompanyDatabaseStructureInfo(date, filename);
//        UpdateCompanyDatabase updateCompanyDatabase = new UpdateCompanyDatabase();
//        CompanyXmlReaderImpl companyXmlReader = new CompanyXmlReaderImpl();
//        int recordsNumber = countRecords(filename, date);
//        for (int i = 0; i < recordsNumber; i++) {
//        }
//    }
    @Transactional
    public CompanyDatabaseStructureInfo createCompanyDb(String filename, Date date) {
        //     int recordsCount = countRecords(filename, date);
        CompanyDatabaseStructureInfo companyDatabaseStructureInfo = new CompanyDatabaseStructureInfo(date, filename);
        CompanyDatabaseStructureInfoOperator companyDatabaseStructureInfoOperator =
                new CompanyDatabaseStructureInfoOperator(companyDatabaseStructureInfo);
        CompanyXmlReader companyXmlReader = new CompanyXmlReaderImpl();
        companyXmlReader.Open(filename, date);
        List<Company> companyList = new LinkedList<>();
        CompanyRecordReadResult companyRecordReadResult;
        Company company;
        for (int i = 0; i < 1607880; i++) {

            companyRecordReadResult = companyXmlReader.readNextRecord();
            companyDatabaseStructureInfoOperator.update(companyRecordReadResult);
            company = companyRecordReadResult.getCompany();
            companyList.add(company);
            if ((i % 1000)==0) {
                companyDAO.addCompanies(companyList);
                companyList.clear();

                LocalDateTime now1 = LocalDateTime.now();
                try {
                    InetAddress iAddress = InetAddress.getLocalHost();
                    String currentIp = iAddress.getHostAddress();
                    LogEvent logEvent = new LogEvent("record # " + i, now1, currentIp);
                    logEventService.save(logEvent);
                }
                catch(Exception e){}
            }
        }
        if (companyList.size() > 0)
            companyDAO.addCompanies(companyList);

        return companyDatabaseStructureInfoOperator.get();
    }

    public boolean schemaValidation(String xmlFilename, String schemaFilename) {
        try {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(xmlFilename));

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(schemaFilename));

            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(reader));
            //no exception thrown, so valid
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}