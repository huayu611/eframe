package com.huayu.eframe.server.config.properties;

import com.huayu.eframe.server.common.PropertiesReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/8/20.
 */
@Service("com.huayu.eframe.server.config.properties.PropertiesLoadService")
public class PropertiesLoadService extends PropertiesReader
{
    @Value(value = "classpath*:/META-INF/config/*_config.properties")
    private Resource[] resource;

    @Value(value = "classpath*:/META-INF/config/ext/*_config.properties")
    private Resource[] extResource;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        read(resource);

        read(extResource);
    }

    public Resource[] getResource()
    {
        return resource;
    }

    public void setResource(Resource[] resource)
    {
        this.resource = resource;
    }

    public Resource[] getExtResource()
    {
        return extResource;
    }

    public void setExtResource(Resource[] extResource)
    {
        this.extResource = extResource;
    }
}
