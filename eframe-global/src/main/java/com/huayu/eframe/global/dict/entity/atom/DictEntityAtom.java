package com.huayu.eframe.global.dict.entity.atom;

import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import com.huayu.eframe.server.common.FramePaging;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Leo on 2019/8/24.
 */
public interface DictEntityAtom
{
    DictEntity addDictEntity(DictEntity dictEntity);

    DictEntity updateDictEntity(DictEntity dictEntity);

    DictEntity queryDictEntityByInnerCode(String innerCode);

    List<DictEntity> queryDictEntityByDictCode(String dictCode);

    Page<DictEntity> queryDictEntityByCode(FramePaging framePaging,DictEntity dictEntity);

    List<DictEntity> queryAll();
}
