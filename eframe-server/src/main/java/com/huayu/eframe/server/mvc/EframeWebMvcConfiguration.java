package com.huayu.eframe.server.mvc;

import com.huayu.eframe.server.mvc.handler.EFrameHandlerMethodReturnValueHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;

/**
 * Created by Administrator on 2018/7/16.
 */
@Configuration
public class EframeWebMvcConfiguration extends DelegatingWebMvcConfiguration
{
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        ResourceHandlerRegistration registration = registry.addResourceHandler("/main/**");
        registration.addResourceLocations("classpath:/static/main/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(new EFrameHandlerMethodReturnValueHandler());
    }
}
