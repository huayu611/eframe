package com.huayu.eframe.server.config;

import com.huayu.eframe.server.config.table.flow.addparameter.AddParameterBusiness;
import com.huayu.eframe.server.config.table.flow.addparameter.AddParameterRequest;
import com.huayu.eframe.server.flow.Flow;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Configuration
@RestController
@RequestMapping(value = "/setting")
public class ParameterServer
{
    @ResponseBody
    @RequestMapping(value = "/Parameter/AddParameter",method = RequestMethod.POST,consumes ={MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public Object addParameter(@RequestBody AddParameterRequest request, EasyParam easyParam)
        {
            Object obj = Flow.execute(AddParameterBusiness.class,request,easyParam);
            return obj;
        }
}
