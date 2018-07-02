/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.NoSql.CompanyHash;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("companyDAO")
public class CompanyDAOImpl extends AbstractDao<Integer, Company> implements CompanyDAO {


    @Override
    public void add(Company company) {
        persist(company);
    }

    @Override
    public void addCompanies(List<Company> companies) {
        add(companies);
    }

    @Override
    public void updateCompany(Company company) {
        update(company);
    }

    @Override
    public void updateCompanies(List<Company> companies) {
        update(companies);
    }

    @Override
    public Company getCompanyById(int id) {
        return getByKey(id);
    }

    @Override
    public void delete(int[] ids) {
        Company company;
        for (int id : ids) {
            company = getCompanyById(id);
            delete(company);
        }
    }

    @Override
    public List<Company> getCompaniesByEDRPOU(String code) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.like("EDRPOU", code));
        crit.setMaxResults(1000);
        return (List<Company>) crit.list();
    }


    @Override
    public List<Company> list(String pattern, String column) {
        Criteria crit = createEntityCriteria();

        crit.add(Restrictions.like(column, "%" + pattern + "%"));
        crit.setFirstResult(0);
        crit.setMaxResults(1000);
        return (List<Company>) crit.list();
    }

    @Override
    public List<Company> list(int firstResult, int Maxresult) {
        Criteria crit = createEntityCriteria();

        crit.setFirstResult(0);
        crit.setMaxResults(1000);
        return (List<Company>) crit.list();
    }

    @Override
    public List<CompanyHash> getHashMap() {
        List<CompanyHash> result = new LinkedList<CompanyHash>();
        Criteria crit = createEntityCriteria();
        crit
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("EDRPOU"), "EDRPOU")

                        .add(Projections.property("hashCode"), "hashCode"))

                .setResultTransformer(Transformers.aliasToBean(CompanyHash.class));
        result = crit.list();


        return result;

    }

    @Override
    public long count() {
        Criteria crit = createEntityCriteria();
        return (Long) crit
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public long notEmptyColumn(String col) {
        Criteria crit = createEntityCriteria();
        return (long) crit.setProjection(Projections.rowCount()).add(Restrictions.isNotNull(col)).uniqueResult();

    }

    @Override
    public long distinctColumnValues(String col) {
        Criteria crit = createEntityCriteria();
        crit.setProjection(Projections.countDistinct(col));
        return (long) crit.list().size();
    }

}