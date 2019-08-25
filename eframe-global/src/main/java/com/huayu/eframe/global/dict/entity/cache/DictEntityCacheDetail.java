package com.huayu.eframe.global.dict.entity.cache;

import com.huayu.eframe.global.dict.flow.Dict;

import java.util.List;

/**
 * Created by Leo on 2019/8/25.
 */
public class DictEntityCacheDetail
{
    private String dictCode;

    private List<Dict> dicts;

    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public List<Dict> getDicts()
    {
        return dicts;
    }

    public void setDicts(List<Dict> dicts)
    {
        this.dicts = dicts;
    }
}
