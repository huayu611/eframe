package com.huayu.eframe.server.security.config;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Leo on 2019/3/1.
 */
@Service
public class WhiteRequestCheck
{
    private List<WhiteRequest> whiteRequestList;

    public WhiteRequestCheck(List<WhiteRequest> whiteRequest)
    {
        whiteRequestList = whiteRequest;
    }

    public boolean checkRequest(HttpServletRequest httpServletRequest)
    {
        if (CollectionUtils.isEmpty(whiteRequestList))
        {
            return false;
        }
        for (WhiteRequest whiteRequest : whiteRequestList)
        {
            boolean result = whiteRequest.checkWhite(httpServletRequest);
            if (result)
            {
                return result;
            }
        }
        return false;
    }
}
