package com.huayu.eframe.flow.presist.service;

import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2019/2/13.
 */
public interface LogService
{
    LogDetail addLog(LogDetail logDetail);

    LogDetail updateLog(LogDetail logDetail);

    LogDetail queryLogByCode(String logCode);

    PageObject queryLogByPage(LogDetail logDetail, PagingRequest pagingRequest);
}
