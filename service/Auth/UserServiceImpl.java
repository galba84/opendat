/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service.Auth;


import com.opendat.dao.SQL.UserDao;
import com.opendat.model.SqlDb.Auth.User;
import com.opendat.model.NoSql.UserStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public void save(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}
	public void updateService(User user){
        User currentUser = 	findById((user.getId()));
        if (!currentUser.getPassword().equals(user.getPassword()))
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setUserProfiles(user.getUserProfiles());
        currentUser.setState(user.getState());

		dao.update(currentUser);
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySso(String sso) {
		return dao.findBySSO(sso);
	}
	public List<User> findAll() {
		return dao.findAll();
	}

	@Override
	public UserStatistic statistic() {
		UserStatistic us = new UserStatistic(dao.statistics());
		return us;
	}
}
