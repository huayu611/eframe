package com.huayu.eframe.global.dict.reader;

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
    public MessageSource getDictMessage(DictInternationalReader dictInternationalReader)
    {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames(dictInternationalReader.getAllResource());
        source.setDefaultEncoding("UTF-8");
        return source;
    }


}
