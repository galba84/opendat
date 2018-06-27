/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Auth.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public void save(User user) {
        persist(user);
    }

    public void updateDao(User user) {

        update(user);
    }


    public User findById(int id) {
        return getByKey(id);
    }

    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        return (User) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("ssoId"));
        return (List<User>) crit.list();
    }

    @Override
    public void update(long[] ids) {
        User u;
        Criteria crit;
        for (long id : ids) {
            crit = createEntityCriteria();
            crit.add(Restrictions.eq("id", id));
            u = (User) crit.uniqueResult();
            update(u);
        }
    }

    public long statistics() {
        Criteria crit = createEntityCriteria();
        crit.setProjection(Projections.rowCount());
        long rowCount;
        List result = crit.list();
        if (!result.isEmpty()) {
            return (long) result.get(0);
        }
        return 0;
    }

}
