package com.gomuki.gomuki.demo.service;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// am urmat un tutorialt pt SecurityConfig si ServerConfig,
// care ajuta la crearea legaturii https
// si implica si generarea unui certificat SSL ( care se face din command prompt)

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
    }
}