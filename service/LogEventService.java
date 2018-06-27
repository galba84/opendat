/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;

import com.opendat.model.SqlDb.Log.LogEvent;

import java.util.List;

public interface LogEventService {

	void save(LogEvent logEvent);
	List<LogEvent> findAll();
	void message2log(String message);

	
}