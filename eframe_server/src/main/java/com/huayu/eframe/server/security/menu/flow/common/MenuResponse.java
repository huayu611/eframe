package com.huayu.eframe.server.security.menu.flow.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huayu.eframe.server.security.menu.service.MenuDetail;

/**
 * Created by Leo on 2019/2/12.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuResponse
{
    private MenuDetail menuDetail;

    public MenuDetail getMenuDetail()
    {
        return menuDetail;
    }

    public void setMenuDetail(MenuDetail menuDetail)
    {
        this.menuDetail = menuDetail;
    }
}
