package com.huayu.eframe.global.system.log.server;

import com.huayu.eframe.flow.Flow;
import com.huayu.eframe.global.system.log.flow.QueryOperatorLogBusiness;
import com.huayu.eframe.global.system.log.message.QueryOperatorLogRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Leo on 2019/3/5.
 */
@Configuration
@RestController
@RequestMapping(value = "/eframe/manage")
public class LogQueryRestServer
{
    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Object queryLog(
            @RequestParam(name="size",required = false) Integer size,
            @RequestParam(name="page",required = false) Integer page,
            @RequestParam(name="operatorCode",required = false) String operatorCode,
            @RequestParam(name="operatorType",required = false) String operatorType,
            @RequestParam(name="url",required = false) String url,
            @RequestParam(name="inTime",required = false) Date inTime,
            @RequestParam(name="outTime",required = false) Date outTime,
            EasyParam easyParam)
    {
        QueryOperatorLogRequest queryOperatorLogRequest = new QueryOperatorLogRequest();
        queryOperatorLogRequest.setInTime(inTime);
        queryOperatorLogRequest.setOutTime(outTime);
        queryOperatorLogRequest.setOperatorCode(operatorCode);
        queryOperatorLogRequest.setOperatorType(operatorType);
        queryOperatorLogRequest.setUrl(url);

        PagingRequest pagingRequest = new PagingRequest();
        pagingRequest.setSize(size);
        pagingRequest.setPage(page);
        queryOperatorLogRequest.setPage(pagingRequest);
        Object obj = Flow.execute(QueryOperatorLogBusiness.class, queryOperatorLogRequest, easyParam);
        return obj;
    }
}
