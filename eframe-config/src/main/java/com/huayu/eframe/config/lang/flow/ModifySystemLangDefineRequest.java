package com.huayu.eframe.config.lang.flow;

import com.huayu.eframe.flow.annotation.EFrameRequest;

/**
 * Created by Leo on 2019/8/2.
 */
public class ModifySystemLangDefineRequest
{
    @EFrameRequest(required = true,length = 255)
    private String languageCode;

    @EFrameRequest(length = 255)
    private String languageName;

    public String getLanguageCode()
    {
        return languageCode;
    }

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getLanguageName()
    {
        return languageName;
    }

    public void setLanguageName(String languageName)
    {
        this.languageName = languageName;
    }
}
