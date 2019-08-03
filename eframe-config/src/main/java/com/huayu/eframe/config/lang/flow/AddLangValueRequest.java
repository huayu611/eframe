package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;
import com.huayu.eframe.server.common.i18n.table.service.LangValueDetail;

import java.util.Map;

/**
 * Created by Leo on 2019/8/3.
 */
public class AddLangValueRequest
{
    @EFrameRequest(required = true,length = 255)
    private String foreignCode;

    private Map<String, LangValueDetail> values;

    public String getForeignCode()
    {
        return foreignCode;
    }

    public void setForeignCode(String foreignCode)
    {
        this.foreignCode = foreignCode;
    }

    public Map<String, LangValueDetail> getValues()
    {
        return values;
    }

    public void setValues(Map<String, LangValueDetail> values)
    {
        this.values = values;
    }
}
