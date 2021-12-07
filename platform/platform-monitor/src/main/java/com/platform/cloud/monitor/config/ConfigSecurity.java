package com.platform.cloud.monitor.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 创建时间:2021/12/7
 * 创建人:pmc
 * 描述:
 */
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
    }
}