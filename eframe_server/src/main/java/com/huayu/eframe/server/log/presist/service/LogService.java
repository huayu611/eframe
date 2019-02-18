package com.huayu.eframe.server.log.presist.service;

import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;

/**
 * Created by Leo on 2019/2/13.
 */
public interface LogService
{
    LogDetail addLog(LogDetail logDetail);

    LogDetail updateLog(LogDetail logDetail);

    LogDetail queryLogByCode(String logCode);

    PageObject queryLogByPage(LogDetail permissionDetail, PagingRequest pagingRequest);
}
