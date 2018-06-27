/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;


import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.NoSql.CompanyHash;

import java.util.List;

public interface CompanyDAO {
    void delete(int[] ids);

    void add(Company company);

    void addCompanies(List<Company> companies);

    Company getCompanyById(int id);

    List<Company> getCompaniesByEDRPOU(String code);

    List<Company> list(String pattern, String column);

    List<Company> list(int firstResult, int maxResult);

    long count();

    long notEmptyColumn(String col);

    long distinctColumnValues(String col);

    void updateCompany(Company company);

    void updateCompanies(List<Company> companies);

    List<CompanyHash> getHashMap();

}
