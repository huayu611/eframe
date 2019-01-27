package com.huayu.eframe.server.dict.reader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Leo on 2019/1/7.
 */
@XmlRootElement(name="dicts")
public class DictBO
{

    private List<Dictionaries> dictionaries;

    @XmlElement(name="dict")
    public List<Dictionaries> getDictionaries()
    {
        return dictionaries;
    }

    public void setDictionaries(List<Dictionaries> dictionaries)
    {
        this.dictionaries = dictionaries;
    }
}
