/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Company.Founder;

import java.util.List;

public interface FounderDAO {
    void add(Founder founder);

    void addFounders(List<Founder> founders);

}
