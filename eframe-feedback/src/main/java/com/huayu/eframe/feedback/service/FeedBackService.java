package com.huayu.eframe.feedback.service;

import com.huayu.eframe.feedback.requset.DeleteFeedBackRequest;
import com.huayu.eframe.feedback.requset.FeedBackRequest;
import com.huayu.eframe.feedback.response.FeedBackResponse;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;

import java.util.Date;

/**
 * Created by Leo on 2018/11/24.
 */
public interface FeedBackService
{
    void addFeedBack(FeedBackRequest request);

    void deleteFeedBack(DeleteFeedBackRequest request);

    FeedBackResponse queryFeedBack();

    PageObject queryFeedBackByPage(PagingRequest page);

    PageObject queryFeedBackByPageAndCondition(PagingRequest page, Date start,Date end,String content);

}
