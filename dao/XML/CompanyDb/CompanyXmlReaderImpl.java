/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.XML.CompanyDb;

import com.opendat.dao.XML.CompanyDb.CompanyXmlReader;
import com.opendat.model.NoSql.ResultSet.CompanyRecordReadResult;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.NoSql.CompanyUpdateStatistic;
import com.opendat.model.SqlDb.Company.Founder;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CompanyXmlReaderImpl implements CompanyXmlReader {

    private String sourceXmlFile;
    private long totalRecordsCount;
    private Company company;
    private XMLInputFactory xmlInputFactory;
    private Date date;
    private XMLEventReader xmlEventReader;
    private XMLEvent xmlEvent;
    private List<String> founders;
    private CompanyRecordReadResult companyRecordReadResult;

    @Override
    public void Open(String fileName, Date date) {
        xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty("javax.xml.stream.isCoalescing", true);
        setXMLvariables(fileName, date);
        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
        } catch (Exception e) {
        }
    }

    private void setXMLvariables(String fileName, Date date) {
        sourceXmlFile = fileName;
        this.date = date;

    }

    @Override
    public void CloseReader() {
        try {
            xmlEventReader.close();
            sourceXmlFile = null;
            this.date = null;
        } catch (Exception e) {
        }
    }

    @Override
    public long countRecords() {
        totalRecordsCount = 0;
        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(sourceXmlFile));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("RECORD")) {
                        totalRecordsCount++;
                    }
                }
            }
        } catch (Exception e) {
        }
        return totalRecordsCount;
    }


    @Override
    public CompanyRecordReadResult readNextRecord() {

        try {
            while (xmlEventReader.hasNext()) {

                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    parseStartElement(startElement);
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (parseEndElement(endElement))
                        return companyRecordReadResult;

                }
            }
        } catch (Exception e) {
        }
        return companyRecordReadResult;
    }

    private void parseStartElement(StartElement startElement) {
        try {
            if (startElement.getName().getLocalPart().equals("RECORD")) {
                company = new Company();
                companyRecordReadResult=new CompanyRecordReadResult();
                company.setDate(date);
                company.setArchived(false);
            } else if (startElement.getName().getLocalPart().equals("NAME")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setNAME(xmlEvent.asCharacters().getData());
            } else if ((startElement.getName().getLocalPart().equals("SHORT_NAME"))) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setSHORT_NAME(xmlEvent.asCharacters().getData());
            } else if (startElement.getName().getLocalPart().equals("EDRPOU")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setEDRPOU(xmlEvent.asCharacters().getData());
            } else if (startElement.getName().getLocalPart().equals("ADDRESS")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setADDRESS(xmlEvent.asCharacters().getData());
            } else if (startElement.getName().getLocalPart().equals("BOSS")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setBOSS(xmlEvent.asCharacters().getData());
            } else if (startElement.getName().getLocalPart().equals("KVED")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setKVED(xmlEvent.asCharacters().getData());
            } else if (startElement.getName().getLocalPart().equals("STAN")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters())
                    company.setSTAN(xmlEvent.asCharacters().getData());
            } else if (startElement.getName().getLocalPart().equals("FOUNDERS")) {
   //             xmlEvent = xmlEventReader.nextEvent();
                founders = new ArrayList<String>();
            } else if (startElement.getName().getLocalPart().equals("FOUNDER")) {
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isCharacters()) {
                    founders.add(xmlEvent.asCharacters().getData());
                }
            }
        } catch (Exception e) {
        }
    }

    private boolean parseEndElement(EndElement endElement) {

        if (endElement.getName().getLocalPart().equals("FOUNDERS")) {
            // add company founders to string field inside Company Entity
            String[] foundersArray = new String[founders.size()];
            foundersArray = founders.toArray(foundersArray);
            String foundersAsString = (Arrays.toString(foundersArray));
            company.setFOUNDERS(foundersAsString);
            companyRecordReadResult.setFounders(founders);
            founders.clear();
        } else if (endElement.getName().getLocalPart().equals("RECORD")) {
            company.setHashCode(company.hashCode());
            companyRecordReadResult.setCompany(company);
            return true;
        }
        return false;
    }


}
