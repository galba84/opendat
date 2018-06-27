/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Log.LogEvent;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("EventLogDAO")
public class EventLogDAOImpl extends AbstractDao<Integer, LogEvent> implements EventLogDAO {


    @Override
    public void add(LogEvent logEvent) {
        persist(logEvent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LogEvent> list() {

        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("id"));
        return (List<LogEvent>) crit.list();
    }
}