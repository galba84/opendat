/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller;

import com.opendat.model.SqlDb.Log.LogEvent;
import com.opendat.model.SqlDb.Auth.User;
import com.opendat.model.SqlDb.Auth.UserProfile;
import com.opendat.service.*;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
import com.opendat.util.Authentification.AuthTools;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("admin")
public class AdminController {
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

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", authTools.getPrincipal());
        model.addAttribute("users", userService.findAll());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        LogEvent logEvent = new LogEvent("admin ", now1, ip);
        logEventService.save(logEvent);
        model.addAttribute("usersstat", userService.statistic());
        model.addAttribute("compstat", companyStatService.findAll());
        return "Admin/admin";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid User user,
                                   BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "Admin/newuser";
        }
        userService.save(user);
        System.out.println("First Name : " + user.getFirstName());
        System.out.println("Last Name : " + user.getLastName());
        System.out.println("SSO ID : " + user.getSsoId());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Checking UsrProfiles....");
        if (user.getUserProfiles() != null) {
            for (UserProfile profile : user.getUserProfiles()) {
                System.out.println("Profile : " + profile.getType());
            }
        }
        model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
        return "Admin/registrationsuccess";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String saveUserRegistration(@Valid User user,
                                       BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "Admin/newuser";
        }
        userService.save(user);
        System.out.println("First Name : " + user.getFirstName());
        System.out.println("Last Name : " + user.getLastName());
        System.out.println("SSO ID : " + user.getSsoId());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Checking UsrProfiles....");
        if (user.getUserProfiles() != null) {
            for (UserProfile profile : user.getUserProfiles()) {
                System.out.println("Profile : " + profile.getType());
            }
        }
        model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
        return "Admin/registrationsuccess";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "Admin/newuser";
    }

    /*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    public String edituser(@RequestParam(value = "checkedId", required = false) long checkedId, ModelMap model) {
        model.addAttribute("id", checkedId);
        model.addAttribute("user", userService.findById((int) checkedId));
        return "Admin/edituser";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public String edituser(@Valid User user,
                           BindingResult result, @RequestParam(value = "checkedId", required = false) Integer checkedId, ModelMap model) {
//		if (toDelete != null && toDelete.length > 0)
        if (result.hasErrors()) {
            System.out.println("There are errors");
            return "Admin/edituser";
        }

        userService.updateService(user);

//        model.addAttribute("id", checkedId);
//		model.addAttribute("user", userService.findById((int)checkedId));
        return "Admin/registrationsuccess";
    }

    @RequestMapping(value = "/loadxml", method = {RequestMethod.GET, RequestMethod.POST})
    public String readXml(Model model, @RequestParam(value = "filename", required = false) String filename, @RequestParam(value = "schemafilename", required = false) String schemafilename, @RequestParam(value = "date", required = false) String dateInput) {

        Date date = null;
        if ((dateInput != "") & (filename != "") & (schemafilename != "")) {
            if (tools.ValidateFileNotEmpty(filename)) {
                date = tools.parseDatefromString(dateInput);

            } else model.addAttribute("infolabel", "XML file is not valid");


        } else
            model.addAttribute("infolabel", "choose DATE and XML database");

        return "Admin/dbstat";
    }

    @RequestMapping(value = "/statistics")
    public String searchLog(@RequestParam(defaultValue = "aaa", required = false) String pattern, Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();
        LogEvent logEvent = new LogEvent("stat request ", now, ip);
        logEventService.save(logEvent);
        model.addAttribute("statistics", logEventService.findAll());


        return "Admin/statistics";
    }

}
