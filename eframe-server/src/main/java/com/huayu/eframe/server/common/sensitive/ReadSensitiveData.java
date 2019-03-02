package com.huayu.eframe.server.common.sensitive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Leo on 2019/2/19.
 */
@Component
@ConfigurationProperties(prefix = "sensitive")
@PropertySource("classpath:META-INF/sensitive/sensitive_eframe.properties")
public class ReadSensitiveData
{
    private String data;

    private String ignore;

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getIgnore()
    {
        return ignore;
    }

    public void setIgnore(String ignore)
    {
        this.ignore = ignore;
    }
}
