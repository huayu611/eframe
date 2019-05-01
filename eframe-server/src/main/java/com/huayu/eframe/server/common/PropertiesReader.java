package com.huayu.eframe.server.common;

import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Leo on 2019/1/7.
 */
public abstract class PropertiesReader implements InitializingBean
{
    private static final LogDebug debug = new LogDebug(PropertiesReader.class);

    private Map<Object, Object> systemMap;

    public PropertiesReader()
    {
        systemMap = new HashMap<>();
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {

    }

    public void read(Resource[] requestResource)
    {
        if (requestResource.length == 0)
        {
            return;
        }

        for (Resource res : requestResource)
        {

            try
            {
                if (null != res)
                {

                    Properties properties = new Properties();
                    properties.load(res.getInputStream());
                    systemMap.putAll(properties);
                }
            }
            catch (IOException e)
            {
                System.out.print("System parameter load file and file name is " + res.getFilename());
            }
        }
    }


    public Object getValue(Object key)
    {
        if (null == key)
        {
            return null;
        }
        String keyString = key.toString();
        if (systemMap.containsKey(keyString))
        {
            return systemMap.get(keyString);
        }
        return null;
    }
}
