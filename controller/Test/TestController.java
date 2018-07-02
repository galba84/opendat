/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller.Test;

import com.opendat.TestNewFunc.TestNewFuncService;
import com.opendat.model.NoSql.ApplicationEnviroupmentValiables.CompanyDatabaseStructureInfo;
import com.opendat.model.NoSql.CompanyStatistic;
import com.opendat.model.NoSql.NaturanLanguageProcessing.ListOfUnicLegalForms;
import com.opendat.model.SqlDb.Company.Company;
import com.opendat.service.*;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
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
import java.util.Map;


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
    @Autowired
    TestNewFuncService testNewFuncService;
    @Autowired
    MailService mailService;

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
    public String SmartParseXml(Model model, String filenameT, String dateT) {
        List<String> stringList = new LinkedList<>();
        Date date1 = tools.parseDatefromString("2018-06-05");//yyyy-MM-dd
        filenameT = "D:\\db\\05_06_18\\15-ufop\\15.1-EX_XML_EDR_UO_UTF.xml";
        CompanyDatabaseStructureInfo companyDatabaseStructureInfo = testNewFuncService.parseXML(filenameT, date1);//"D:\\db\\05_06_18\\15-ufop\\15.1-EX_XML_EDR_UO_UTF.xml"
        //    model.addAttribute("contacts", companies);
        model.addAttribute("companyDatabaseStructureInfo", companyDatabaseStructureInfo);
        model.addAttribute("infolabel", "analizeXmlFile  ");
        return "ResultView/updateDbResult";
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

    @RequestMapping(value = "/details", method = {RequestMethod.POST})
    public String details(ModelMap model, String checkedId) {

        Company company = companyService.getCompanyById(Integer.parseInt(checkedId));
        List<Company> lc = new LinkedList<>();
        lc.add(company);
        model.addAttribute("contacts", lc);
        model.addAttribute("infolabel", "details " + checkedId);
        return "index";
    }

}