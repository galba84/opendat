/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller;

import com.opendat.model.NoSql.CompanyStatistic;
import com.opendat.model.NoSql.NaturanLanguageProcessing.ListOfUnicLegalForms;
import com.opendat.service.*;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
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
@RequestMapping("test")
public class TestController {

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


    @RequestMapping(value = "fakeuser", method = {RequestMethod.GET, RequestMethod.POST})
    public String fakeuser(@RequestParam(value = "selectedId", required = false) long[] selectedId,
                           ModelMap model) {
        //   User editUser=null;
        //     if (toDelete != null && toDelete.length > 0)
        // editUser = (User)userService.findById((int)toDelete[0]);
        long[] id = {1, 2, 3, 4, 5};
        model.addAttribute("id", id);
        //    toDelete[0]=10;

        return "fakeuser";
    }


    @RequestMapping(value = "SmartParseXml", method = {RequestMethod.POST})
    public String SmartParseXml(Model model, String filenameT) {
        List<String> stringList = new LinkedList<>();
        Date date1 = tools.parseDatefromString("2222-22-22");
        ListOfUnicLegalForms listOfUnicLegalForms = companyService.SmartParseXml(filenameT, date1, 200000);
        model.addAttribute("contacts", listOfUnicLegalForms);
        model.addAttribute("infolabel", "SmartParseXml listofUnicLF.size()" + listOfUnicLegalForms.sizeOfMap());
        return "ResultView/result1";
    }


    @RequestMapping(value = "/dbstat", method = {RequestMethod.GET})
    public String dbstat(ModelMap model) {
        model.addAttribute("compstat", companyStatService.findAll());
        model.addAttribute("infolabel", "chose an option");
        return "/Test/dbstat";
    }

    @RequestMapping(value = "/dbstat", method = {RequestMethod.POST})
    public String dbstatupdate(ModelMap model, @RequestParam(value = "filename", required = false) String filename, @RequestParam(value = "action", required = false) String action, @RequestParam(value = "date", required = false) String date) {
        CompanyStatistic cs = companyService.Statistic();
        companyStatService.save(cs);
        model.addAttribute("Statistic updated", filename);
        model.addAttribute("compstat", companyStatService.findAll());

        return "/Test/dbstat";
    }
}