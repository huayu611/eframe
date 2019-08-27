package com.huayu.eframe.server.common.restful;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;

/**
 * Created by Leo on 2018/10/15.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PagingResponse
{
    private Long total;

    private int totalPage;

    private int currentPage;

    public PagingResponse()
    {

    }

    public PagingResponse(Page page)
    {
        total = page.getTotalElements();
        currentPage = page.getNumber();
        totalPage = page.getTotalPages();
    }

    public Long getTotal()
    {
        return total;
    }

    public void setTotal(Long total)
    {
        this.total = total;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
}
