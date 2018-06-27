/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Company.CompanyArchived;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyArchivedDAO")
public class CompanyArchivedDAOImpl extends AbstractDao<Integer, CompanyArchived> implements CompanyArchivedDAO {


    @Override
    public void add(CompanyArchived companyArchived) {
        persist(companyArchived);
    }


    @Override
    public void addCompaniesArchived(List<CompanyArchived> archivedCompanies) {
        add(archivedCompanies);
    }

    @Override
    public CompanyArchived getCompanyArchivedById(int id) {
        return getByKey(id);
    }


    @Override
    public List<CompanyArchived> list(String pattern, String column) {
        Criteria crit = createEntityCriteria();
        if (column.equalsIgnoreCase("EDRPOU")) {
            int intValue = Integer.parseInt(pattern);
            crit.add(Restrictions.like(column, intValue));
        } else {
            crit.add(Restrictions.like(column, "%" + pattern + "%"));
        }
        crit.setFirstResult(0);
        crit.setMaxResults(1000);
        return (List<CompanyArchived>) crit.list();
    }

    @Override
    public long count() {
        Criteria crit = createEntityCriteria();
        return (Long) crit
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public void updateCompany(CompanyArchived companyArchived) {
        update(companyArchived);
    }
}