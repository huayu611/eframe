package com.huayu.eframe.global.dict.reader;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Leo on 2019/1/7.
 */
@XmlRootElement(name="value")
public class DictDetail
{

    private String code;

    private String langCode;

    private String name;

    @XmlAttribute(name="code")
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
    @XmlAttribute(name="lang")
    public String getLangCode()
    {
        return langCode;
    }

    public void setLangCode(String langCode)
    {
        this.langCode = langCode;
    }

    @XmlAttribute(name="name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
