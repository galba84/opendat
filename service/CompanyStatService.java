/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;


import com.opendat.model.NoSql.CompanyStatistic;

import java.util.List;

public interface CompanyStatService {

	void save(CompanyStatistic companyStatistic);
	public List<CompanyStatistic> findAll();

}