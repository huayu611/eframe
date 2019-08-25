package com.huayu.eframe.global.dict.entity.service;

import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;

import java.util.List;

/**
 * Created by Leo on 2019/8/24.
 */

public interface DictEntityService
{
    DictEntityDetail addDictEntity(DictEntityDetail dictEntityDetail);

    DictEntityDetail updateDictEntity(DictEntityDetail dictEntityDetail);

    String removeDictEntity(String innerCode);

    PageObject queryDictEntity(PagingRequest pageRequest, DictEntityDetail dictEntityDetail);

    DictParamDetail addDictParam(DictParamDetail dictParamDetail);

    DictParamDetail updateDictParam(DictParamDetail dictParamDetail);

    String removeDictParam(String code);

    List<DictParamDetail> queryDictParamByDictEntity(String dictInnerDictDode);
}
