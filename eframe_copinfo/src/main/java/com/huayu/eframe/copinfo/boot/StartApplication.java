package com.huayu.eframe.copinfo.boot;

import com.huayu.eframe.server.log.Debug;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Leo on 2019/1/18.
 */
@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.huayu"})
@ComponentScan(basePackages = {"com.huayu"})
@EntityScan(basePackages = {"com.huayu"})
@EnableConfigurationProperties
@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication
public class StartApplication
{
    private static Debug debug = new Debug(StartApplication.class);

    public static void main(String[] args) {
        start(args);
    }

    public static void start(String[] args)
    {
        com.huayu.eframe.server.boot.StartApplication.start(args);

        debug.debug("Boot Start success");
    }
}
