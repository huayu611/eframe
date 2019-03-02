package com.huayu.eframe.global.dict.reader;

import com.huayu.eframe.server.common.i18n.property.InternationalReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/2/28.
 */
@Service
public class DictInternationalReader extends InternationalReader
{
    //\META-INF\config\dict\lang
    @Value(value = "classpath*:/META-INF/config/dict/lang/*_dict.properties")
    private Resource[] resource;
    @Override
    public void afterPropertiesSet() throws Exception
    {
        read(resource);
    }

    @Override
    protected String getPath()
    {
        return "classpath:META-INF/config/dict/lang/";
    }
}
