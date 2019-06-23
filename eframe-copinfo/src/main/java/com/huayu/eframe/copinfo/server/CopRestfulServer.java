package com.huayu.eframe.copinfo.server;

import com.huayu.eframe.copinfo.server.flow.copbaseinfo.AddCopBaseInfoBusiness;
import com.huayu.eframe.copinfo.server.flow.copbaseinfo.CopBaseInfoMessage;
import com.huayu.eframe.copinfo.server.flow.copbaseinfo.ModifyCopBaseInfoBusiness;
import com.huayu.eframe.copinfo.server.flow.copbaseinfo.QueryCopBaseInfoBusiness;
import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2019/1/18.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/enterprise")
public class CopRestfulServer
{
    @ResponseBody
    @RequestMapping(value = "/information",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })

    public Object addEnterprise(@RequestBody CopBaseInfoMessage modifyStaffInfomation,
                                EasyParam easyParam)
    {
        Object obj = Flow.execute(AddCopBaseInfoBusiness.class, modifyStaffInfomation, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/information",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })

    public Object queryEnterprise(EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryCopBaseInfoBusiness.class, null, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/information",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public Object updateEnterprise(@RequestBody CopBaseInfoMessage message, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyCopBaseInfoBusiness.class, message, easyParam);
        return obj;
    }
}
