package com.huayu.eframe.global.dict;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.dict.flow.QueryDictBusiness;
import com.huayu.eframe.global.dict.flow.entity.*;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Leo on 2019/1/7.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/dict")
public class DictionaryRestServer
{
    @ResponseBody
    @RequestMapping(value = "/{code}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryDictByCode(@PathVariable String code, EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryDictBusiness.class, code, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/dict-entity", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryDictEntity(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "dictCode", required = false) String dictCode,
            @RequestParam(name = "dictName", required = false) String dictName,
            EasyParam easyParam) {

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);

        QueryDictEntityRequest queryDictEntityRequest = new QueryDictEntityRequest();
        queryDictEntityRequest.setPagingRequest(pagingRequest);
        queryDictEntityRequest.setDictCode(dictCode);
        queryDictEntityRequest.setDictName(dictName);
        Object obj = Flow.execute(QueryDictEntityBusiness.class, queryDictEntityRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/dict-entity", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object updateDictEntity(@RequestBody ModifyDictEntityRequest modifyDictEntityRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyDictEntityBusiness.class, modifyDictEntityRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/dict-entity", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addDictEntity(@RequestBody AddDictEntityRequest addDictEntityRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddDictEntityBusiness.class, addDictEntityRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/dict-entity/{code}", method = RequestMethod.DELETE,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object removeDictEntity(@PathVariable String code, EasyParam easyParam)
    {
        RemoveDictEntityRequest removeDictEntityRequest = new RemoveDictEntityRequest();
        removeDictEntityRequest.setDictInnerCode(code);
        Object obj = Flow.execute(RemoveDictEntityBusiness.class, removeDictEntityRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/dict-entity-param", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object addDictParam(@RequestBody AddDictParamEntityRequest addDictParamEntityRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(AddDictParamEntityBusiness.class, addDictParamEntityRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/dict-entity-param", method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryDictParam(
            @RequestParam(name = "dictInnerCode", required = true) String dictInnerCode,
            EasyParam easyParam)
    {
        QueryDictParamEntityRequest queryDictParamEntityRequest = new QueryDictParamEntityRequest();
        queryDictParamEntityRequest.setDictInnerCode(dictInnerCode);
        Object obj = Flow.execute(QueryDictParamEntityBusiness.class, queryDictParamEntityRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/dict-entity-param", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object updateDictParam(@RequestBody ModifyDictParamEntityRequest modifyDictParamEntityRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(ModifyDictParamEntityBusiness.class, modifyDictParamEntityRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/dict-entity-param/{code}", method = RequestMethod.DELETE,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object removeDictParam(@PathVariable String code, EasyParam easyParam)
    {
        RemoveDictParamEntityRequest removeDictParamEntityRequest = new RemoveDictParamEntityRequest();
        removeDictParamEntityRequest.setCode(code);
        Object obj = Flow.execute(RemoveDictParamEntityBusiness.class, removeDictParamEntityRequest, easyParam);
        return obj;
    }
}
