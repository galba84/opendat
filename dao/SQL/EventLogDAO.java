/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.dao.SQL;

import com.opendat.model.SqlDb.Log.LogEvent;

import java.util.List;

public interface EventLogDAO {

    void add(LogEvent logEvent);

    List<LogEvent> list();

}
