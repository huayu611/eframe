package com.huayu.eframe.global.system;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.system.cache.query.QueryAllSystemCacheBusiness;
import com.huayu.eframe.global.system.cache.query.QueryAllSystemCacheRequest;
import com.huayu.eframe.global.system.cache.refresh.RefreshCacheBusiness;
import com.huayu.eframe.global.system.cache.refresh.RefreshCacheRequest;
import com.huayu.eframe.global.system.currentlogin.QueryCurrentLoginBusiness;
import com.huayu.eframe.global.system.lang.QuerySystemLangBusiness;
import com.huayu.eframe.global.system.lang.QuerySystemLangRequest;
import com.huayu.eframe.global.system.log.flow.QueryOperatorLogBusiness;
import com.huayu.eframe.global.system.log.message.QueryOperatorLogRequest;
import com.huayu.eframe.global.system.switchdebug.SwitchDebugBusiness;
import com.huayu.eframe.global.system.switchdebug.SwitchDebugRequest;
import com.huayu.eframe.global.system.token.QuerySystemTokensBusiness;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Leo on 2019/4/9.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/system-information")
public class SystemControlServer
{
    @ResponseBody
    @RequestMapping(value = "/current/login",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Object getCurrentLogin(EasyParam easyParam)
    {
        Object obj = Flow.execute(QueryCurrentLoginBusiness.class, null, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryLog(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "operatorCode", required = false) String operatorCode,
            @RequestParam(name = "operatorType", required = false) String operatorType,
            @RequestParam(name = "url", required = false) String url,
            @RequestParam(name = "inTime", required = false) Date inTime,
            @RequestParam(name = "outTime", required = false) Date outTime,
            @RequestParam(name = "method", required = false) String method,
            EasyParam easyParam)
    {
        QueryOperatorLogRequest queryOperatorLogRequest = new QueryOperatorLogRequest();
        queryOperatorLogRequest.setInTime(inTime);
        queryOperatorLogRequest.setOutTime(outTime);
        queryOperatorLogRequest.setOperatorCode(operatorCode);
        queryOperatorLogRequest.setOperatorType(operatorType);
        queryOperatorLogRequest.setUrl(url);
        queryOperatorLogRequest.setMethod(method);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryOperatorLogRequest.setPage(pagingRequest);
        Object obj = Flow.execute(QueryOperatorLogBusiness.class, queryOperatorLogRequest, easyParam);
        return obj;
    }


    @ResponseBody
    @RequestMapping(value = "/system-tokens",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Object getCurrentTokens(EasyParam easyParam)
    {
        Object obj = Flow.execute(QuerySystemTokensBusiness.class, null, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/switch-log",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object switchLog(@RequestBody SwitchDebugRequest switchDebugRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(SwitchDebugBusiness.class, switchDebugRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/refresh-cache",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object refreshCache(@RequestBody RefreshCacheRequest refreshCacheRequest, EasyParam easyParam)
    {
        Object obj = Flow.execute(RefreshCacheBusiness.class, refreshCacheRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/query-cache",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Object queryAllCacheData(EasyParam easyParam)
    {
        QueryAllSystemCacheRequest queryAllSystemCacheRequest = new QueryAllSystemCacheRequest();
        Object obj = Flow.execute(QueryAllSystemCacheBusiness.class, queryAllSystemCacheRequest, easyParam);
        return obj;
    }

    @ResponseBody
    @RequestMapping(value = "/languages",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Object getSystemSupportLanguages(EasyParam easyParam)
    {
        QuerySystemLangRequest querySystemLangRequest = new QuerySystemLangRequest();
        Object obj = Flow.execute(QuerySystemLangBusiness.class, querySystemLangRequest, easyParam);
        return obj;
    }
}
