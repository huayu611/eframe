package com.huayu.eframe.server.dict.reader;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by Leo on 2018/9/25.
 */
@Configuration
public class DictConfigurature
{
    @Bean("DictConfigurature")
    public MessageSource getExceptionResource()
    {
        ReloadableResourceBundleMessageSource source =  new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:META-INF/config/dict/lang/frame_dict");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

}
