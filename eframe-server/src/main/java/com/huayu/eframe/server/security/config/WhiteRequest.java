package com.huayu.eframe.server.security.config;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leo on 2019/3/1.
 */
public interface WhiteRequest
{
    boolean checkWhite(HttpServletRequest httpServletRequest);
}
