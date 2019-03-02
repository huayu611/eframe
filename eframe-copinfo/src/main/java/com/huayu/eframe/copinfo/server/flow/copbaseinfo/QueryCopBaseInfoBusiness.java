package com.huayu.eframe.copinfo.server.flow.copbaseinfo;

import com.huayu.eframe.copinfo.server.serivce.CopBaseInfoService;
import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class QueryCopBaseInfoBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(QueryCopBaseInfoBusiness.class);

    private final static String RESULT= "QueryCopBaseInfoBusiness_RESULT";
    @Autowired
    private CopBaseInfoService copBaseInfoService;

    @Override
    public void execute(BusinessParameter param)
    {
        debug.log("Begin Query Cop Info");
        CopBaseInfoDetail result = copBaseInfoService.query();
        param.addParameter(RESULT,result);
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        CopBaseInfoDetail detail = param.getParameter(RESULT);
        QueryCopInfoResponse queryCopInfoResponse = new QueryCopInfoResponse();
        queryCopInfoResponse.setDetail(detail);
        return queryCopInfoResponse;
    }
}
