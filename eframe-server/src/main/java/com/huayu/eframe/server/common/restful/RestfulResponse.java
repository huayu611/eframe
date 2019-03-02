package com.huayu.eframe.server.common.restful;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Administrator on 2018/7/18.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestfulResponse
{
    private String code;

    private String msg;

    private Long total;

    private Integer currentPage;

    private Integer totalPage;

    private Object data;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public Long getTotal()
    {
        return total;
    }

    public void setTotal(Long total)
    {
        this.total = total;
    }

    public Integer getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }
}
