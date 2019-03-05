package com.huayu.eframe.global.system;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.system.currentlogin.QueryCurrentLoginBusiness;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Leo on 2019/3/5.
 */

@Configuration
@RestController
@RequestMapping(value = "/eframe/system-information")
public class SystemInfoQueryRestfulServer
{

    @ResponseBody
    @RequestMapping(value = "/current/login",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object getCurrentLogin(EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryCurrentLoginBusiness.class, null, easyParam);
        return obj;
    }
}
