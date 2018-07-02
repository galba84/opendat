/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller;


import com.opendat.TestNewFunc.TestNewFuncService;
import com.opendat.model.NoSql.Enumerations.ErrorCodes.EXIT_CODE;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.model.SqlDb.Log.LogEvent;
import com.opendat.service.*;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
import com.opendat.util.Validators.CompanySearchRequestValidator;
import com.opendat.util.Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("search")
public class SearchController {

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


    @RequestMapping(value = "search", method = {RequestMethod.GET, RequestMethod.POST})
    public String search(@RequestParam(required = false) String pattern, @RequestParam("RadioGroupSearch") String column, Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        LocalDateTime now1 = LocalDateTime.now();
        CompanySearchRequestValidator companySearchRequestValidator = new CompanySearchRequestValidator();
        int searchRequesrValidationChechResult = companySearchRequestValidator.validateRequest(pattern, column);

        LogEvent logEvent = new LogEvent("search " + StringUtils.abbreviate(pattern, 128) + " EXIT_CODE " + EXIT_CODE.getNameByCode(searchRequesrValidationChechResult), now1, ip);
        logEventService.save(logEvent);


        if (searchRequesrValidationChechResult == 0) {
            List<Company> lc = companyService.SearchCompanies(pattern, column);
            model.addAttribute("infolabel", "search \"" + pattern + "\" in " + column + ". Found " + lc.size() + " records");
            model.addAttribute("contacts", lc);
        } else {
            model.addAttribute("infolabel", "search error: " + EXIT_CODE.getNameByCode(searchRequesrValidationChechResult) + " Please try again");
        }
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        LogEvent logEvent = new LogEvent("index ", now1, ip);
        logEventService.save(logEvent);
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        LogEvent logEvent = new LogEvent("about ", now1, ip);
        logEventService.save(logEvent);
        return "about";
    }


}
