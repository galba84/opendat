/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.NoSql.CompanyStatistic;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

//import com.opendat.springsecurity.model.Group;

@Repository("companyStatisticDAO")
public class CompanyStatisticDAOImpl extends AbstractDao<Integer, CompanyStatistic> implements CompanyStatisticDAO {


    @Override
    public void add(CompanyStatistic companyStatistic) {
        persist(companyStatistic);
    }


    @Override
    public CompanyStatistic getCompanyStatisticById(int id) {
        return getByKey(id);
    }


    @Override
    public List<CompanyStatistic> list(String pattern, String column) {
        Criteria crit = createEntityCriteria();
        if (column.equalsIgnoreCase("EDRPOU")) {
            int intValue = Integer.parseInt(pattern); //this code will work for this.
            crit.add(Restrictions.like(column, intValue));

        } else {
            crit.add(Restrictions.like(column, "%" + pattern + "%"));
        }
        crit.setFirstResult(0);
        crit.setMaxResults(1000);

        return (List<CompanyStatistic>) crit.list();
    }

    @Override
    public List<CompanyStatistic> list() {
        Criteria crit = createEntityCriteria();
        return (List<CompanyStatistic>) crit.list();
    }

    @Override
    public long count() {
        Criteria crit = createEntityCriteria();
        return (Long) crit
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }


}