package com.opendat.dao.XML.EnviroupmentVariables;

import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.CompanyDatabaseStructureInfo;
import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.Variables;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLEncoderDecoder
{
    public XMLEncoderDecoder() {
    }
    //    public static void main(String[] args) throws IOException
//    {
//        UserSettings settings = new UserSettings();
//        settings.setFieldOne(10000);
//        settings.setFieldTwo("HowToDoInJava.com");
//        settings.setFieldThree(false);
//
//        //Before
//        System.out.println(settings);
//        serializeToXML ( settings );
//        UserSettings loadedSettings = deserializeFromXML();
//        //After
//        System.out.println(loadedSettings);
//    }
//
    public void serializeToXML (Variables variables) throws IOException
    {
        FileOutputStream fos = new FileOutputStream("settings.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
         //       System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(variables);
        encoder.close();
        fos.close();
    }

//    private static UserSettings deserializeFromXML() throws IOException {
//        FileInputStream fis = new FileInputStream("settings.xml");
//        XMLDecoder decoder = new XMLDecoder(fis);
//        UserSettings decodedSettings = (UserSettings) decoder.readObject();
//        decoder.close();
//        fis.close();
//        return decodedSettings;
//    }
}