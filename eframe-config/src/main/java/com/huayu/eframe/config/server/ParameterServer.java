package com.huayu.eframe.config.server;

import com.huayu.eframe.config.flow.addparameter.AddParameterBusiness;
import com.huayu.eframe.config.flow.addparameter.AddParameterRequest;
import com.huayu.eframe.config.flow.delete.DeleteParameterBusiness;
import com.huayu.eframe.config.flow.modify.ModifyParameterBusiness;
import com.huayu.eframe.config.flow.modify.ModifyParameterRequest;
import com.huayu.eframe.config.flow.query.QueryParameterBusiness;
import com.huayu.eframe.flow.Flow;
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
    @RequestMapping(value = "/parameter", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addParameter(@RequestBody AddParameterRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddParameterBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/parameter", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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
    @RequestMapping(value = "/parameter/{codes}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteParameter(@PathVariable String codes, EasyParam easyParam)
    {
        Object obj = Flow.execute(DeleteParameterBusiness.class, codes, easyParam);
        return obj;
    }
}
