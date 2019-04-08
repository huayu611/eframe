package com.huayu.eframe.global.system.token;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leo on 2019/4/8.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/system-information")
public class QuerySystemTokensServer
{
    @ResponseBody
    @RequestMapping(value = "/system-tokens",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object getCurrentLogin(EasyParam easyParam)
    {
        Object obj = Flow.execute(QuerySystemTokensBusiness.class, null, easyParam);
        return obj;
    }
}
