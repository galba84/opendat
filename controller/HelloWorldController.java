/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.opendat.model.SqlDb.Log.LogEvent;
import com.opendat.service.*;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
import com.opendat.util.Authentification.AuthTools;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Controller
public class HelloWorldController {
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
    AuthTools authTools;

    static final int DEFAULT_GROUP_ID = -1;
    private static final int ITEMS_PER_PAGE = 8;


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        LocalDateTime now1 = LocalDateTime.now();
        LogEvent logEvent = new LogEvent("/home, /  ", now1, ip);
        logEventService.save(logEvent);
        model.addAttribute("greeting", "Hi, Welcome to OPENDAT");
        return "index";
    }


    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", authTools.getPrincipal());
        return "dba";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String dbaPage(ModelMap model, String message, String email, String name) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        LocalDateTime now1 = LocalDateTime.now();
        LogEvent logEvent = new LogEvent("Message was sent" + message + " email " + email + " name " + name, now1, ip);
        logEventService.save(logEvent);
        model.addAttribute("infolabel", "Message was sent");
        return "index";
    }


}