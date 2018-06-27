/*
 * Copyright (c) 2018. Oleksandr Sereda
 */

package com.opendat.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests().antMatchers(HttpMethod.DELETE).denyAll();
        http.authorizeRequests().antMatchers(HttpMethod.PATCH).denyAll();
        http.authorizeRequests().antMatchers(HttpMethod.PUT).denyAll();
        //     http.authorizeRequests().antMatchers(HttpMethod.POST).anonymous();
        http.authorizeRequests()
                .antMatchers("/", "/home", "/index", "**/search/**","test/**", "**/result", "**/fakeuser", "/editUser/**", "/test/**").permitAll()
                .antMatchers("/admin/**", "/newuser").access("hasRole('ADMIN')")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .antMatchers("/statistics", "/readXml").access("hasRole('USER') or hasRole('DBA')or hasRole('ADMIN')")
                .and().formLogin().loginPage("/login/login")
                .usernameParameter("ssoId").passwordParameter("password")

                .and().exceptionHandling().accessDeniedPage("/Access_Denied")
                .and().csrf().disable();

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index");
        //              .logoutSuccessHandler(logoutSuccessHandler)
        //            .invalidateHttpSession(true);
        //            .addLogoutHandler(logoutHandler)
        //              .deleteCookies(cookieNamesToClear);
    }
}
