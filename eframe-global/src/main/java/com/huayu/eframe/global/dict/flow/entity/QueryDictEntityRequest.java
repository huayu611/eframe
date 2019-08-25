package com.huayu.eframe.global.dict.flow.entity;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.restful.PagingRequest;

/**
 * Created by Leo on 2019/8/24.
 */
public class QueryDictEntityRequest
{

    private PagingRequest pagingRequest;

    @EFrameRequest(length = 255)
    private String dictCode;

    @EFrameRequest(length = 255)
    private String dictName;

    public PagingRequest getPagingRequest()
    {
        return pagingRequest;
    }

    public void setPagingRequest(PagingRequest pagingRequest)
    {
        this.pagingRequest = pagingRequest;
    }

    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }
}
