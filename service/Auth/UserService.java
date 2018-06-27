/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service.Auth;


import com.opendat.model.SqlDb.Auth.User;
import com.opendat.model.NoSql.UserStatistic;

import java.util.List;

public interface UserService {

	void save(User user);
	void updateService(User user);


	User findById(int id);
	
	User findBySso(String sso);
	List<User> findAll();

	UserStatistic statistic();
	
}