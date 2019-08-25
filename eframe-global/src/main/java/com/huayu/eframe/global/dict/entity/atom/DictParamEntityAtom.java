package com.huayu.eframe.global.dict.entity.atom;

import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import com.huayu.eframe.global.dict.entity.bo.DictParamEntity;

import java.util.List;

/**
 * Created by Leo on 2019/8/24.
 */
public interface DictParamEntityAtom
{
    DictParamEntity addDictParamEntity(DictParamEntity dictParamEntity);

    DictParamEntity updateDictParamEntity(DictParamEntity dictParamEntity);

    void removeDictParamEntity(DictParamEntity dictParamEntity);

    List<DictParamEntity> queryDictParamByDict(DictEntity dictEntity);

    DictParamEntity queryDictParamByEntityAndKey(DictEntity dictEntity,String key);

    DictParamEntity queryDictParamByCode(String code);

    List<DictParamEntity> queryAll();
}
