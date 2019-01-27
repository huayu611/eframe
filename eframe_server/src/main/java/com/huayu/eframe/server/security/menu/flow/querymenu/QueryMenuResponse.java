package com.huayu.eframe.server.security.menu.flow.querymenu;

import com.huayu.eframe.server.flow.restful.RestfulResponse;

import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
public class QueryMenuResponse
{
    private List<MenuDetail> menuDetails;

    public List<MenuDetail> getMenuDetails()
    {
        return menuDetails;
    }

    public void setMenuDetails(List<MenuDetail> menuDetails)
    {
        this.menuDetails = menuDetails;
    }
}
