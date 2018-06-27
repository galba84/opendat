/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;


import com.opendat.model.SqlDb.Company.CompanyArchived;

import java.util.List;


public interface CompanyArchivedDAO {

    void add(CompanyArchived companyArchived);

    void addCompaniesArchived(List<CompanyArchived> archivedCompanies);

    CompanyArchived getCompanyArchivedById(int id);

    List<CompanyArchived> list(String pattern, String column);

    long count();

    void updateCompany(CompanyArchived companyArchived);

}
