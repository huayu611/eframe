package com.huayu.eframe.server.service.exception;

import com.huayu.eframe.server.common.i18n.property.InternationalReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/2/28.
 */
@Service
public class ExceptionInternationalReader extends InternationalReader
{
    @Value(value = "classpath*:/META-INF/exception/*_exception.properties")
    private Resource[] resource;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        read(resource);
    }

    @Override
    protected String getPath()
    {
        return "classpath:META-INF/exception/";
    }
}
