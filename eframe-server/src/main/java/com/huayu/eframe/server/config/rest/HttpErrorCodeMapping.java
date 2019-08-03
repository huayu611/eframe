package com.huayu.eframe.server.config.rest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class HttpErrorCodeMapping implements InitializingBean
{

    @Value(value = "classpath*:/META-INF/config/*_rest_error_code.properties")
    private Resource[] resource;

    @Value(value = "classpath*:/META-INF/config/ext/*_rest_error_code.properties")
    private Resource[] extResource;

    private Map<Object, Object> systemMap = new HashMap<>();

    public void read(Resource[] restFulResource)
    {
        if (restFulResource.length == 0)
        {
            return;
        }

        for (Resource resource : restFulResource)
        {
            try
            {

                Properties properties = new Properties();
                properties.load(resource.getInputStream());
                systemMap.putAll(properties);

            }
            catch (IOException e)
            {
                System.out.print("System parameter load file and file name is " + resource.getFilename());
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        read(resource);

        read(extResource);
    }

    public Object getValue(String code)
    {
        return systemMap.get(code);
    }
}
