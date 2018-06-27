/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.configuration;

import com.opendat.dao.SQL.UserProfileDao;
import com.opendat.service.Auth.UserProfileService;
import com.opendat.service.Auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebInitializer implements WebApplicationInitializer {
    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;
    @Autowired
    UserProfileDao userProfileDao;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration charEncodingFilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingFilterReg.setInitParameter("forceEncoding", "true");
        charEncodingFilterReg.addMappingForUrlPatterns(null, true, "/*");

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.setConfigLocation(getClass().getPackage().getName());
    }
}

