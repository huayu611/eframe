package com.huayu.eframe.global.dict.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Created by Leo on 2018/9/25.
 */
@Configuration
public class DictConfigurature
{
    @Bean("DictConfigurature")
    public MessageSource getExceptionResource(DictInternationalReader dictInternationalReader)
    {
        ReloadableResourceBundleMessageSource source =  new ReloadableResourceBundleMessageSource();
        source.setBasenames(dictInternationalReader.getAllResource());
        source.setDefaultEncoding("UTF-8");
        return source;
    }




}
