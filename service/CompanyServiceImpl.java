/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;


import com.opendat.dao.SQL.CompanyArchivedDAO;
import com.opendat.dao.SQL.CompanyDAO;
import com.opendat.dao.SQL.FounderDAO;
import com.opendat.model.NoSql.CompanyStatistic;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.util.CompanyServiceTools.ParseXml.*;
import com.opendat.util.DataStructuresOperators.CompanyDbStatisticOperator;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)

    public CompanyStatistic Statistic() {

        CompanyDbStatisticOperator companyDbStatisticOperator = new CompanyDbStatisticOperator();
        return companyDbStatisticOperator.getStatistic();
    }
    @Override
    @Transactional(readOnly = true)
    public Company getCompanyById(int id){
        return companyDAO.getCompanyById(id);
    }

}
