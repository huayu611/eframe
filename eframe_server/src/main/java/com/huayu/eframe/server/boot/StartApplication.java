package com.huayu.eframe.server.boot;

import com.huayu.eframe.server.common.sensitive.Sensitive;
import com.huayu.eframe.server.log.Debug;
import com.huayu.eframe.server.service.spring.BeanPool;
import com.huayu.eframe.server.tool.basic.PropertiesUtils;
import com.huayu.eframe.server.tool.encrypt.AESEncrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

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

    private static final String KEY = "1234567890ZYXWVU";

    public static void main(String[] args) {

        start(args);
    }

    public static void start(String[] args)
    {
        readSystemProperty();
        resolvePassword();
        debug.debug(System.getProperty("mysql.password"));
        SpringApplication.run(StartApplication.class, args);
        debug.debug("Boot Start success");
    }



    public static void readSystemProperty()
    {
        String proFilePath = System.getProperty("user.dir") + "\\eframe_system.properties";
        InputStream in = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(proFilePath));
            System.getProperties().load(in);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void resolvePassword()
    {
        String password = System.getProperty("mysql.password");
        String value = password;
        try
        {
             value = AESEncrypt.aesEcbDecode(password, KEY);
        }
        catch (Exception e)
        {
            return;
        }
        System.setProperty("mysql.password",value);
    }


}
