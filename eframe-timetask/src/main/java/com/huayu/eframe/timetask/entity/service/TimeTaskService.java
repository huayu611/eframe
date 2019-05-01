package com.huayu.eframe.timetask.entity.service;

import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2019/4/15.
 */
public interface TimeTaskService
{

    TimeTaskDetail addTimeTask(TimeTaskDetail timeTaskDetail);

    TimeTaskDetail updateTimeTask(TimeTaskDetail timeTaskDetail);

    TimeTaskDetail queryTimeTaskByCode(String timeTaskCode);

    PageObject queryTimeTaskByPage(TimeTaskDetail timeTaskDetail, PagingRequest pagingRequest);

    String deleteTimeTask(String code);

    void updateTimeTaskExecute(TimeTaskDetail timeTaskDetail);

    PageObject queryTimeTaskInstanceByPage(TimeTaskInstanceDetail timeTaskDetail, PagingRequest pagingRequest);

}
