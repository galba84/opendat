/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;

import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.NoSql.CompanyStatistic;

import java.util.List;

public interface CompanyService {

    void AddCompany(Company company);

    void AddCompanies(List<Company> companies);

    void UpdateCompanyService(Company company);

    void UpdateCompaniesService(List<Company> companies);

    long Count();

    List<Company> SearchCompanies(String pattern, String column);

    CompanyStatistic Statistic();

    Company getCompanyById(int id);


}
