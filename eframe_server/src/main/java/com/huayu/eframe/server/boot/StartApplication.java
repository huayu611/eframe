package com.huayu.eframe.server.boot;

import com.huayu.eframe.server.common.sensitive.Sensitive;
import com.huayu.eframe.server.log.Debug;
import com.huayu.eframe.server.service.spring.BeanPool;
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
public class StartApplication
{
    private static Debug debug = new Debug(StartApplication.class);

    public static void main(String[] args) {

        start(args);
    }

    public static void start(String[] args)
    {
        SpringApplication.run(StartApplication.class, args);

        debug.debug("Boot Start success");
    }


}
