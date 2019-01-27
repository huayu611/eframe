package com.huayu.eframe.server.dict.flow;

import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/1/7.
 */
public class DictResponse
{
    private List<Dict> dicts;

    public List<Dict> getDicts()
    {
        return dicts;
    }

    public void setDicts(List<Dict> dicts)
    {
        this.dicts = dicts;
    }
}
