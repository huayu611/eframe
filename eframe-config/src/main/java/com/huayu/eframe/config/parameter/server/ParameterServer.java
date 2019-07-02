package com.huayu.eframe.config.parameter.server;

import com.huayu.eframe.config.parameter.flow.addparameter.AddParameterBusiness;
import com.huayu.eframe.config.parameter.flow.addparameter.AddParameterRequest;
import com.huayu.eframe.config.parameter.flow.delete.DeleteParameterBusiness;
import com.huayu.eframe.config.parameter.flow.delete.DeleteParameterRequest;
import com.huayu.eframe.config.parameter.flow.modify.ModifyParameterBusiness;
import com.huayu.eframe.config.parameter.flow.modify.ModifyParameterRequest;
import com.huayu.eframe.config.parameter.flow.query.QueryParameterBusiness;
import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Configuration
@RestController
@RequestMapping(value = "/eframe/setting")
public class ParameterServer
{
    @ResponseBody
    @RequestMapping(value = "/parameter", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addParameter(@RequestBody AddParameterRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddParameterBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/parameter", method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryParameter(EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryParameterBusiness.class, null, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/parameter", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyParameter(@RequestBody ModifyParameterRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyParameterBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/parameter/{codes}", method = RequestMethod.DELETE,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteParameter(@PathVariable String codes, EasyParam easyParam)
    {
        DeleteParameterRequest deleteParameterRequest = new DeleteParameterRequest();
        deleteParameterRequest.setParameterCode(codes);
        Object obj = Flow.execute(DeleteParameterBusiness.class, deleteParameterRequest, easyParam);
        return obj;
    }
}
