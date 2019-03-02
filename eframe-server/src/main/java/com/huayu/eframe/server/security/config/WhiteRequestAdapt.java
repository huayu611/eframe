package com.huayu.eframe.server.security.config;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Leo on 2019/3/1.
 */
@Service
public class WhiteRequestAdapt implements WhiteRequest
{
    @Override
    public boolean checkWhite(HttpServletRequest httpServletRequest)
    {
        return false;
    }
}
