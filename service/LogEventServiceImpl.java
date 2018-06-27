/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.service;


import com.opendat.dao.SQL.EventLogDAO;
import com.opendat.model.SqlDb.Log.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;

@Service("LogEventService")
@Transactional
public class LogEventServiceImpl implements LogEventService  {

	@Autowired
	private EventLogDAO dao;

    @Override
    @Transactional
	public void save(LogEvent logEvent){
			dao.add(logEvent);
	}

    @Override
    @Transactional
	public List<LogEvent> findAll() {
		return dao.list();
	}

    @Override
    @Transactional
    public void message2log (String message){
	try {
        LocalDateTime now1 = LocalDateTime.now();
        InetAddress iAddress = InetAddress.getLocalHost();
        String currentIp = iAddress.getHostAddress();
        LogEvent logEvent = new LogEvent(message,now1,currentIp);
        dao.add(logEvent);
    }
    catch (Exception ex){}
	}

}
