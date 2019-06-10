package com.huayu.eframe.server.security.send;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Leo on 2019/5/6.
 */
@Configuration
public class RestfulTemplateBean
{
    @Bean("JsonRestTemplate")
    public RestTemplate restJsonTemplate() {

        //这个restful 的添加TEXT_PLAIN作为json返回 ，部分大厂，返回text-plain作为json。
        RestTemplate restTemplate =  new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM,MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    @Bean("XmlRestTemplate")
    public RestTemplate restXmlTemplate() {

        RestTemplate restTemplate =  new RestTemplate();
        MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
        mappingJackson2XmlHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2XmlHttpMessageConverter);
        return restTemplate;
    }


}
