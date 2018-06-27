/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;


import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.NoSql.CompanyStatistic;
import com.opendat.model.NoSql.CompanyUpdateStatistic;
import com.opendat.model.NoSql.NaturanLanguageProcessing.ListOfUnicLegalForms;

import java.util.Date;
import java.util.List;

public interface CompanyService {

    void AddCompany(Company company);

    void AddCompanies(List<Company> companies);

    void UpdateCompanyService(Company company);

    void UpdateCompaniesService(List<Company> companies);

    long Count();

    List<Company> SearchCompanies(String pattern, String column);


    List<Company> SetHashCodes();
    CompanyStatistic Statistic();

    List<Company> GetNewRecordsFromXml(String filename, Date date, Integer amount);
    ListOfUnicLegalForms SmartParseXml (String filename, Date date, Integer amount);

}
