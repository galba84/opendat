/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.model.NoSql.ResultSet;

import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.SqlDb.Company.Founder;

import java.util.List;

public class CompanyRecordReadResult {

    Company company;
    List<String> founders;

    public CompanyRecordReadResult() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<String> getFounders() {
        return founders;
    }

    public void setFounders( List<String>  founders) {
        this.founders = founders;
    }
}
