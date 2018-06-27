/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util.CompanyServiceTools.ParseXml;

import org.springframework.stereotype.Component;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;

@Component

public class ParseXmlCompanyServiceTools {

    public XMLEventReader MoveCoursorOfEventReaderToOffsetPosition(XMLEventReader xmlEventReader, long startReadOffest) {
        long position = 0;
        while ((position < startReadOffest) & xmlEventReader.hasNext()) {
            try {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("RECORD")) {
                        position++;
                        while (position < startReadOffest) {
                            xmlEvent = xmlEventReader.nextEvent();
                            if (xmlEvent.isStartElement()) {
                                startElement = xmlEvent.asStartElement();
                                if (startElement.getName().getLocalPart().equals("RECORD"))
                                    position++;

                            }
                            xmlEvent = xmlEventReader.nextEvent();

                        }
                    }

                }
            } catch (Exception exc) {
            }
        }
        return xmlEventReader;
    }

    public boolean SchemaValidation(String xmlFilename, String schemaFilename) {
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