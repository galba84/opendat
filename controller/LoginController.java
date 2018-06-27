/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.controller;


import com.opendat.model.SqlDb.Auth.UserProfile;
import com.opendat.service.*;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
import com.opendat.util.Authentification.AuthTools;
import com.opendat.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("login")
public class LoginController {

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

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", authTools.getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }


}
