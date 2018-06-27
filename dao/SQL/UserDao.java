/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;


import com.opendat.model.SqlDb.Auth.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User findById(int id);

    User findBySSO(String sso);

    List<User> findAll();

    void update(long[] ids);

    void update(User user);

    long statistics();
}

