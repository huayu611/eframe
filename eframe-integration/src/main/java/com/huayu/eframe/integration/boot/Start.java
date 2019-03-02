package com.huayu.eframe.integration.boot;

import com.huayu.eframe.server.boot.StartApplication;
import com.huayu.eframe.server.log.Debug;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 2018/6/24.
 */
@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.huayu"})
@ComponentScan(basePackages = {"com.huayu"})
@EntityScan(basePackages = {"com.huayu"})
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class Start
{

    public static void main(String[] args) {

        StartApplication.start(args);
    }




}
