package com.huayu.eframe.server.log.presist.service.impl;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;
import com.huayu.eframe.server.flow.restful.PagingResponse;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.log.presist.atom.LogAtom;
import com.huayu.eframe.server.log.presist.bo.LogEntity;
import com.huayu.eframe.server.log.presist.service.LogDetail;
import com.huayu.eframe.server.log.presist.service.LogService;
import com.huayu.eframe.server.mvc.token.TokenUtils;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/2/13.
 */
@Service
public class LogServiceImpl implements LogService
{
    private static final LogDebug debug = new LogDebug(LogServiceImpl.class);

    @Autowired
    private LogAtom logAtom;


    @Override
    public LogDetail addLog(LogDetail logDetail)
    {
        LogEntity logEntity = buildLogEntity(logDetail);
        fixCreateInfo(logEntity);
        LogEntity logEntityNew = logAtom.addLog(logEntity);
        debug.log(logEntityNew);
        LogDetail logDetailResult = buildLogDetail(logEntityNew);
        return logDetailResult;
    }

    @Override
    public LogDetail updateLog(LogDetail logDetail)
    {
        LogEntity logEntity = getLogEntityByCode(logDetail.getCode());
        debug.log(logEntity);
        buildDetailEntity(logDetail, logEntity);
        LogEntity newLogEntity = logAtom.updateLog(logEntity);
        debug.log(newLogEntity);
        return buildLogDetail(newLogEntity);
    }

    @Override
    public LogDetail queryLogByCode(String logCode)
    {
        LogEntity logEntity = getLogEntityByCode(logCode);
        return buildLogDetail(logEntity);
    }

    @Override
    public PageObject queryLogByPage(LogDetail logDetail, PagingRequest pagingRequest)
    {
        FramePaging framePaging = new FramePaging();
        if (null != pagingRequest)
        {
            framePaging.setPage(pagingRequest.getPage());
            framePaging.setSize(pagingRequest.getSize());
        }
        LogEntity entity = buildLogEntity(logDetail);
        Page<LogEntity> logResult = logAtom.queryLog(framePaging, entity);
        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(logResult.getTotalElements());
        pagingResponse.setCurrentPage(logResult.getNumber());
        pagingResponse.setTotalPage(logResult.getTotalPages());

        PageObject pageObject = new PageObject();
        pageObject.setPagingResponse(pagingResponse);
        pageObject.setResponse(getLogEntityResponse(logResult.getContent()));
        return pageObject;
    }

    private List<LogDetail> getLogEntityResponse(List<LogEntity> logEntities)
    {

        if (CollectionUtils.isEmpty(logEntities))
        {
            return new ArrayList<>();
        }
        List<LogDetail> details = new ArrayList<>();
        for (LogEntity backBo : logEntities)
        {
            LogDetail detail = buildLogDetail(backBo);
            details.add(detail);
        }
        return details;
    }

    private LogEntity buildLogEntity(LogDetail logDetail)
    {
        LogEntity logEntity = new LogEntity();
        if (null == logDetail)
        {
            return logEntity;
        }
        logEntity.setLogCode(logDetail.getCode());
        buildDetailEntity(logDetail, logEntity);
        return logEntity;
    }

    private void buildDetailEntity(LogDetail logDetail, LogEntity logEntity)
    {
        if (null == logEntity || null == logDetail)
        {
            return;
        }

        logEntity.setMethod(logDetail.getMethod());
        logEntity.setRequest(logDetail.getRequest());
        logEntity.setResponse(logDetail.getResponse());
        logEntity.setUrl(logDetail.getUrl());
        logEntity.setInTime(logDetail.getInTime());
        logEntity.setOutTime(logDetail.getOutTime());
        logEntity.setStatus(logDetail.getStatus());
        logEntity.setRequestParameter(logDetail.getRequestParameter());
        logEntity.setRequestIp(logDetail.getRequestIp());
        logEntity.setErrorStack(logDetail.getErrorStack());
        logEntity.setInMillion(logDetail.getInMillion());
        logEntity.setOutMillion(logDetail.getOutMillion());
        //operentity

        TokenInstance tokenInstance = TokenUtils.getTokenInstance();


        if (null != tokenInstance)
        {
            logEntity.setOperObjType(logDetail.getOperObjType());
            logEntity.setOperObjId(tokenInstance.getInstanceIdByCode(logDetail.getOperObjCode()));
        }

    }


    private LogDetail buildLogDetail(LogEntity logEntity)
    {
        if (null == logEntity)
        {
            return null;
        }
        LogDetail logDetail = new LogDetail();
        logDetail.setCode(logEntity.getLogCode());
        logDetail.setKey(logEntity.getLogCode());
        logDetail.setErrorStack(logEntity.getErrorStack());
        logDetail.setInTime(logEntity.getInTime());
        logDetail.setOutTime(logEntity.getOutTime());
        logDetail.setInMillion(logEntity.getInMillion());
        logDetail.setOutMillion(logEntity.getOutMillion());
        logDetail.setMethod(logEntity.getMethod());
        logDetail.setRequest(logEntity.getRequest());
        logDetail.setResponse(logEntity.getResponse());
        logDetail.setStatus(logEntity.getStatus());
        logDetail.setUrl(logEntity.getUrl());
        logDetail.setOperObjType(logEntity.getOperObjType());
        logDetail.setRequestIp(logEntity.getRequestIp());
        logDetail.setRequestParameter(logEntity.getRequestParameter());
        if (null != logEntity.getOperObjType() && null != logEntity.getOperObjId())
        {
            TokenInstance tokenInstance = TokenUtils.getTokenInstance();
            if (null != tokenInstance)
            {
                String code = tokenInstance.getInstanceCodeById(logEntity.getOperObjId());
                logDetail.setOperObjCode(code);
            }
        }


        return logDetail;
    }


    private LogEntity getLogEntityByCode(String code)
    {
        return logAtom.getLogByCode(code);
    }

    private void fixCreateInfo(LogEntity logEntity)
    {
        logEntity.setCreateTime(LocalAttribute.getNow());

    }


}
