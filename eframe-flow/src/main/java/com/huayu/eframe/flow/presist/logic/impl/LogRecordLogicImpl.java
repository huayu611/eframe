package com.huayu.eframe.flow.presist.logic.impl;

import com.huayu.eframe.flow.common.FlowConstant;
import com.huayu.eframe.flow.common.HttpUtils;
import com.huayu.eframe.flow.presist.constant.LogConstants;
import com.huayu.eframe.server.tool.basic.JSonUtils;
import com.huayu.eframe.flow.presist.logic.LogRecordLogic;
import com.huayu.eframe.flow.presist.service.LogDetail;
import com.huayu.eframe.flow.presist.service.LogService;
import com.huayu.eframe.server.common.sensitive.Sensitive;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.handler.EasyParam;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.tool.basic.*;
import com.huayu.eframe.server.tool.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by Leo on 2019/2/17.
 */
@Service
public class LogRecordLogicImpl implements LogRecordLogic
{

    private static final LogDebug debug = new LogDebug(LogRecordLogicImpl.class);

    private static final String LOG_CONTENT = "_LOCAL_CONTENT_RECORD_LOG_PRIMARY";

    private static final Integer MAXLENGTH = Integer.valueOf("2047");

    @Autowired
    private Sensitive sensitive;

    @Autowired
    private LogService logService;


    @Override
    public void initLog(Object request)
    {
        debug.log("Start Record Oper Log");
        LogDetail logDetail = buildLogDetail(request);
        LogDetail logDetailNew = logService.addLog(logDetail);
        LocalAttribute.addValue(LOG_CONTENT, logDetailNew);

    }

    @Override
    public void startLog()
    {
        LogDetail logDetail = LocalAttribute.getValue(LOG_CONTENT);
        logDetail.setStatus(LogConstants.LOG_RUNNING_STATUS);
        LogDetail logDetailNew = logService.updateLog(logDetail);
        LocalAttribute.addValue(LOG_CONTENT, logDetailNew);
    }

    @Override
    public void finishLog(Object response)
    {
        String responseJson = JSonUtils.coverToJson(response);
        LogDetail logDetail = LocalAttribute.getValue(LOG_CONTENT);
        String responseJsonData = StringUtils.getStringByLength(responseJson, MAXLENGTH);
        logDetail.setResponse(sensitive.filterJsonSensitive(responseJsonData));
        Date now = DateUtils.getCurrentDate();
        logDetail.setOutTime(now);
        logDetail.setOutMillion(now.getTime());
        logDetail.setStatus(LogConstants.LOG_FINISH_STATUS);

        if (null == logDetail.getOperObjCode() || null == logDetail.getOperObjType())
        {
            buildOperatorInfo(logDetail);
        }

        LogDetail logDetailNew = logService.updateLog(logDetail);
        LocalAttribute.addValue(LOG_CONTENT, logDetailNew);
    }

    private void buildOperatorInfo(LogDetail logDetail)
    {
        Token token = LocalAttribute.getToken();
        if (null != token)
        {
            logDetail.setOperObjType(token.getPrimaryType());
            logDetail.setOperObjCode(token.getPrimaryCode());
        }
    }

    @Override
    public void errorLog(Throwable exception)
    {
        String stackError = ObjectUtils.getTrace(exception);
        LogDetail logDetail = LocalAttribute.getValue(LOG_CONTENT);
        Date now = DateUtils.getCurrentDate();
        logDetail.setOutTime(now);
        logDetail.setOutMillion(now.getTime());
        logDetail.setStatus(LogConstants.LOG_ERROR_STATUS);
        logDetail.setErrorStack(StringUtils.getStringByLength(stackError, MAXLENGTH));
        LogDetail logDetailNew = logService.updateLog(logDetail);
        LocalAttribute.addValue(LOG_CONTENT, logDetailNew);
    }

    private LogDetail buildLogDetail(Object requestBody)
    {
        LogDetail logDetail = new LogDetail();

        HttpServletRequest request = LocalAttribute.getValue(FlowConstant.HTTP_REQUEST);
        if (null != requestBody)
        {
            String requestJson = JSonUtils.coverToJson(requestBody);
            String json = StringUtils.getStringByLength(requestJson, MAXLENGTH);
            String lastJson = sensitive.filterJsonSensitive(json);
            logDetail.setRequest(StringUtils.getStringByLength(lastJson, 2047));
        }
        logDetail.setCode(generateLogCode());
        logDetail.setKey(logDetail.getCode());
        logDetail.setUrl(request.getRequestURI());
        logDetail.setMethod(request.getMethod());
        logDetail.setStatus(LogConstants.LOG_INIT_STATUS);
        Date now = LocalAttribute.getNow();
        logDetail.setInTime(now);
        logDetail.setInMillion(now.getTime());

        //IP
        String ip = HttpUtils.getIpFromEasyParam(request);
        logDetail.setRequestIp(ip);
        logDetail.setRequestParameter(getRequestParameter());
        buildOperatorInfo(logDetail);
        return logDetail;
    }

    private String getRequestParameter()
    {
        StringBuffer result = new StringBuffer();
        EasyParam easyParam = LocalAttribute.getValue(FlowConstant.EASY_SERVLET);
        Map<String, String[]> requestHeader = easyParam.getRequestHeaders();
        if (MapUtils.isEmpty(requestHeader))
        {
            return "{}";
        }
        String parameterJson = JSonUtils.coverToJson(requestHeader);
        return StringUtils.cutLengthString(parameterJson, 2047);
    }

    private String generateLogCode()
    {
        return "log_" + RandomUtils.getRandomUUID();
    }
}
