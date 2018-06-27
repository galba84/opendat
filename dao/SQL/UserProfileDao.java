/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Auth.UserProfile;

import java.util.List;

public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
