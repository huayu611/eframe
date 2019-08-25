package com.huayu.eframe.global.dict.entity.cache;

import com.huayu.eframe.global.dict.flow.Dict;

import java.util.List;

/**
 * Created by Leo on 2019/8/25.
 */
public interface DictEntityCache
{
    String CACHE_NAME = "SYS_DICT_ENTITY#QUERY_ITEM";

    List<Dict> queryDictByDictCode(String code);
}
