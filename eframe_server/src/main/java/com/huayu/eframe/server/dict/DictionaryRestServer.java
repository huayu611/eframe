package com.huayu.eframe.server.dict;

import com.huayu.eframe.server.dict.flow.QueryDictBusiness;
import com.huayu.eframe.server.flow.Flow;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2019/1/7.
 */
@Configuration
@RestController
@RequestMapping(value = "/dict")
public class DictionaryRestServer
{
    @ResponseBody
    @RequestMapping(value = "/v1/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryDictByCode(@PathVariable String code, EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryDictBusiness.class, code, easyParam);
        return obj;
    }
}
