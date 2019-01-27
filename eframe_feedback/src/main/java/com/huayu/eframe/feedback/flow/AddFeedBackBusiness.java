package com.huayu.eframe.feedback.flow;

import com.huayu.eframe.feedback.requset.FeedBackRequest;
import com.huayu.eframe.feedback.service.FeedBackService;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2018/11/24.
 */
@Service
public class AddFeedBackBusiness extends AbstractExecuteBusiness
{

    @Autowired
    private FeedBackService feedBackService;

    @Override
    public void execute(BusinessParameter param)
    {
        FeedBackRequest feedBackRequest = param.getRequest();
        feedBackService.addFeedBack(feedBackRequest);
    }
}
