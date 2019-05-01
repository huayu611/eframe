package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created by Administrator on 2018/7/13.
 */
@Configuration
@EnableWebSecurity
public class EframeWebSecurityConfig
        extends WebSecurityConfigurerAdapter
{
    private static final LogDebug debug = new LogDebug(EframeWebSecurityConfig.class);

    @Autowired
    private EFrameFilterSecurityInterceptor eFrameFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        debug.log("EframeWebSecurityConfig start");

        http.authorizeRequests().antMatchers("/manage/Login", "/static/**", "/main/**").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(new EFrameRestfulAuthenticationEntryPoint());
        http.headers().frameOptions().disable();

        http.addFilterBefore(eFrameFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf().disable();
    }


}
