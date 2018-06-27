/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Company.Founder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FounderDAO")

public class FounderDAOImpl extends AbstractDao<Integer, Founder> implements FounderDAO {
    @Override
    public void add(Founder founder) {
        persist(founder);
    }

    public void addFounders(List<Founder> founders) {
        for (Founder founder : founders
                ) {
            persist(founder);
        }
    }
}
