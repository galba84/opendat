/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller;

import com.opendat.TestNewFunc.TestNewFuncService;
import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.CompanyDatabaseStructureInfo;
import com.opendat.model.NoSql.CompanyStatistic;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
import com.opendat.service.CompanyService;
import com.opendat.service.CompanyStatService;
import com.opendat.service.LogEventService;
import com.opendat.service.test.MailService;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("logs")
public class LogsController {

    @Autowired
    public CompanyService companyService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;
    @Autowired
    LogEventService logEventService;
    @Autowired
    CompanyStatService companyStatService;
    @Autowired
    Tools tools;
    @Autowired
    TestNewFuncService testNewFuncService;
    @Autowired
    MailService mailService;


    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public String deleteLogs(ModelMap model, String checkedId) {
        model.addAttribute("infolabel", "delete " + checkedId);
        logEventService.delete(checkedId);
        model.addAttribute("statistics", logEventService.findAll());
        return "Admin/statistics";
    }

}