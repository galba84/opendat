/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service.Auth;


import com.opendat.model.SqlDb.Auth.UserProfile;

import java.util.List;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
