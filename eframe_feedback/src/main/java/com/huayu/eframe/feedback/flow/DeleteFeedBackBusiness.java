package com.huayu.eframe.feedback.flow;

import com.huayu.eframe.feedback.requset.DeleteFeedBackRequest;
import com.huayu.eframe.feedback.requset.FeedBackRequest;
import com.huayu.eframe.feedback.service.FeedBackService;
import com.huayu.eframe.server.flow.AbstractExecuteBusiness;
import com.huayu.eframe.server.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Leo on 2018/11/24.
 */
@Service
public class DeleteFeedBackBusiness extends AbstractExecuteBusiness
{

    private static final LogDebug debug = new LogDebug(DeleteFeedBackBusiness.class);

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";


    @Autowired
    private FeedBackService feedBackService;

    @Override
    public void execute(BusinessParameter param)
    {

        DeleteFeedBackRequest request  = param.getRequest();
        if(null != request && null != request.getId())
        {
            feedBackService.deleteFeedBack(request);
        }
        String ids = request.getIds();
        debug.log(ids);
        if(!StringUtils.isEmpty(ids))
        {
            batchDeleteFeedBack(ids);
        }

    }

    private void batchDeleteFeedBack(String ids)
    {
        debug.log(ids);
        String[] nameArr = StringUtils.tokenizeToStringArray(ids, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        debug.log(nameArr.length);
        if(nameArr.length>0)
        {
            for(String id : nameArr)
            {
                Long requestID = NumberUtils.getLongFromObject(id);
                debug.log(requestID);
                DeleteFeedBackRequest request = new DeleteFeedBackRequest();
                request.setId(requestID);
                feedBackService.deleteFeedBack(request);
            }
        }
    }
}
