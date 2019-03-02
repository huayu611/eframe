package com.huayu.eframe.feedback.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by Leo on 2018/11/24.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FeedBackResponse
{
    private List<FeedBackDetail> feedBackDetailList;

    public List<FeedBackDetail> getFeedBackDetailList()
    {
        return feedBackDetailList;
    }

    public void setFeedBackDetailList(List<FeedBackDetail> feedBackDetailList)
    {
        this.feedBackDetailList = feedBackDetailList;
    }
}
