package com.huayu.eframe.global.system.switchdebug;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/4/9.
 */
public class SwitchDebugRequest
{
    @EFrameRequest(required = true, bean = "_dictionary(global-debug-log-level)")
    private Integer level;

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }
}
