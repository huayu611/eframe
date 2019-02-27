package com.huayu.eframe.server.flow;

import com.huayu.eframe.server.config.properties.SystemConfig;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;
import com.huayu.eframe.server.flow.restful.PagingResponse;
import com.huayu.eframe.server.flow.restful.RestfulResponse;
import com.huayu.eframe.server.security.service.request.QueryPermissionRequest;
import com.huayu.eframe.server.tool.basic.StringUtils;

/**
 * Created by Leo on 2018/12/4.
 * 框架中共用API，方法中为protected级别。只允许组内调用，如果外部需要调用，可以用工具类处理
 */
public class FrameCommonAPI
{

    protected final static String MAX_RETURN_COUNT_PARAM = "sys_manager_max_page_size";

    protected final static String MAX_RETURN_DEFAULT_COUNT = "-1";


    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";


    protected void tidyPagingResponse(PageObject pageObject, RestfulResponse response)
    {
        PagingResponse pagingResponse = pageObject.getPagingResponse();
        if (null != pagingResponse)
        {
            response.setTotal(pagingResponse.getTotal());
            response.setTotalPage(pagingResponse.getTotalPage());
            response.setCurrentPage(pagingResponse.getCurrentPage() + 1);
        }
        else
        {
            response.setTotal(0l);
            response.setTotalPage(0);
            response.setCurrentPage(1);
        }
    }

    public void tidyPage(PagingRequest page)
    {

        if (null != page)
        {
            if (null == page.getPage() || page.getPage() < 1)
            {
                page.setPage(Integer.valueOf("1"));
            }
            if (page.getPage() >= 1)
            {
                page.setPage(page.getPage() - 1);
            }
            if(null == page.getSize() || 0 == page.getSize().intValue())
            {
                String max = SystemConfig.getValue(MAX_RETURN_COUNT_PARAM, MAX_RETURN_DEFAULT_COUNT);
                if(StringUtils.equalString(MAX_RETURN_DEFAULT_COUNT,max))
                {
                    page.setSize(Integer.MAX_VALUE);
                }
                else
                {
                    Integer value = Integer.valueOf(max);
                    page.setSize(value);
                }
            }
        }

    }


    protected String deleteInBatch(DeleteInItem deleteInItem,String codes)
    {
        String[] nameArr = org.springframework.util.StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        if(nameArr.length==0)
        {
            return "";
        }
        String result = "";
        for(String code : nameArr)
        {
            String resultCode = deleteInItem.deleteByItem(code);
            result = result + resultCode + ",";
        }
        if(result.endsWith(","))
        {
            result = result.substring(0,result.length()-1);
        }
        return result;
    }

    public  interface DeleteInItem
    {
        String deleteByItem(String code);
    }

}
