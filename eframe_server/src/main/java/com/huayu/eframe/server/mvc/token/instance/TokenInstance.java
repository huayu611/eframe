package com.huayu.eframe.server.mvc.token.instance;

/**
 * Created by Leo on 2019/2/14.
 */
public interface TokenInstance
{
    String getInstanceCodeById(Long id);

    Long getInstanceIdByCode(String code);

    String getType();
}
