/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;


import com.opendat.dao.SQL.CompanyStatisticDAO;
import com.opendat.model.NoSql.CompanyStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("companyStatService")
@Transactional
public class CompanyStatServiceImpl implements CompanyStatService {

	@Autowired
	private CompanyStatisticDAO dao;

    @Override
    @Transactional
	public void save(CompanyStatistic companyStatistic){
			dao.add(companyStatistic);
	}
    @Override
    @Transactional
	public List<CompanyStatistic> findAll() {
		return dao.list();
	}


}
