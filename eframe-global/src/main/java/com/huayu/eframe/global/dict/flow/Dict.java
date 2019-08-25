package com.huayu.eframe.global.dict.flow;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Leo on 2019/1/7.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Dict
{
    private String key;

    private String name;

    private String display;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDisplay()
    {
        return display;
    }

    public void setDisplay(String display)
    {
        this.display = display;
    }
}
