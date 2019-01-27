package com.huayu.eframe.server.common.standard;

import java.util.Date;

/**
 * Created by Leo on 2018/9/18.
 */
//放入这里是为了让缓存过滤能用上
public interface ValidTime
{
    Date getExpireTime();

    Date getEffectiveTime();
}
