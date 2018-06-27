/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.NoSql.CompanyStatistic;

import java.util.List;

public interface CompanyStatisticDAO {

    void add(CompanyStatistic company);

    CompanyStatistic getCompanyStatisticById(int id);

    long count();

    List<CompanyStatistic> list(String pattern, String column);

    List<CompanyStatistic> list();

}
