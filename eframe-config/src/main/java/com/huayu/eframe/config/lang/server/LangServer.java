package com.huayu.eframe.config.lang.server;

import com.huayu.eframe.config.lang.flow.*;
import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2019/8/3.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/lang")
public class LangServer
{
    @ResponseBody
    @RequestMapping(value = "/lang-define", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addLangDefine(@RequestBody AddSystemLangDefineRequest addSystemLangDefineRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddSystemLangDefineBusiness.class, addSystemLangDefineRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-define", method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryLangDefine(EasyParam easyParam)
    {
        QuerySystemLangDefineRequest querySystemLangDefineRequest = new QuerySystemLangDefineRequest();
        Object obj = Flow.execute(QuerySystemLangDefineBusiness.class, querySystemLangDefineRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-define", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyLangDefine(@RequestBody ModifySystemLangDefineRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifySystemLangDefineBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-define/{codes}", method = RequestMethod.DELETE,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object deleteParameter(@PathVariable String codes, EasyParam easyParam)
    {
        RemoveSystemLangDefineRequest removeSystemLangDefineRequest = new RemoveSystemLangDefineRequest();
        removeSystemLangDefineRequest.setLanguageCode(codes);
        Object obj = Flow.execute(RemoveSystemLangDefineBusiness.class, removeSystemLangDefineRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-value", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addLangValue(@RequestBody AddLangValueRequest addLangValueRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddLangValueBusiness.class, addLangValueRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-value", method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryLangValue(EasyParam easyParam)
    {
        QueryLangValueRequest queryLangValueRequest = new QueryLangValueRequest();
        Object obj = Flow.execute(QueryLangValueBusiness.class, queryLangValueRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-value", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyLangValue(@RequestBody ModifyLangValueRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyLangValueBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-value/{codes}", method = RequestMethod.DELETE,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object removeLangValue(@PathVariable String codes, EasyParam easyParam)
    {
        RemoveLangValueRequest removeLangValueRequest = new RemoveLangValueRequest();
        removeLangValueRequest.setForeignCode(codes);
        Object obj = Flow.execute(RemoveLangValueBusiness.class, removeLangValueRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-text", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addLangText(@RequestBody AddLangTextRequest addLangTextRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddLangTextBusiness.class, addLangTextRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-text", method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryLangText(EasyParam easyParam)
    {
        QueryLangTextRequest queryLangTextRequest = new QueryLangTextRequest();
        Object obj = Flow.execute(QueryLangTextBusiness.class, queryLangTextRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-text", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object modifyLangText(@RequestBody ModifyLangTextRequest request, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyLangTextBusiness.class, request, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/lang-text/{codes}", method = RequestMethod.DELETE,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object removeLangText(@PathVariable String codes, EasyParam easyParam)
    {
        RemoveTextValueRequest removeTextValueRequest = new RemoveTextValueRequest();
        removeTextValueRequest.setForeignCode(codes);
        Object obj = Flow.execute(RemoveTextValueBusiness.class, removeTextValueRequest, easyParam);
        return obj;
    }
}
