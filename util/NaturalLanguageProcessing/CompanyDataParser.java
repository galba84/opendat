/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.util.NaturalLanguageProcessing;

import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.NoSql.NaturanLanguageProcessing.ListOfUnicLegalForms;
import com.opendat.model.NoSql.ProcessedCompany;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class CompanyDataParser {

    ListOfUnicLegalForms listOfUnicLegalForms = new ListOfUnicLegalForms();

    ProcessedCompany ParseCompany(Company company) {
        ProcessedCompany processedCompany = new ProcessedCompany();


        return processedCompany;
    }

    public String [] parseName(String initialName) {
        List<String> resultLegalFormAndName = new LinkedList<>();
        String [] result = StringUtils.split(initialName,"\"",2);
        return result;
    }

}
