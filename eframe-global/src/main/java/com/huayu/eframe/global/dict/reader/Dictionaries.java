package com.huayu.eframe.global.dict.reader;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Leo on 2019/1/7.
 */
@XmlRootElement(name="dict")
public class Dictionaries
{

    private String code;

    private List<DictDetail> dictDetail;

    @XmlAttribute(name="code")
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @XmlElement(name="value")
    public List<DictDetail> getDictDetail()
    {
        return dictDetail;
    }

    public void setDictDetail(List<DictDetail> dictDetail)
    {
        this.dictDetail = dictDetail;
    }
}
