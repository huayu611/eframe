package com.huayu.eframe.global.dict.entity.cache.impl;

import com.huayu.eframe.global.dict.entity.atom.DictEntityAtom;
import com.huayu.eframe.global.dict.entity.atom.DictParamEntityAtom;
import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import com.huayu.eframe.global.dict.entity.bo.DictParamEntity;
import com.huayu.eframe.global.dict.entity.cache.DictEntityCache;
import com.huayu.eframe.global.dict.entity.cache.DictEntityCacheDetail;
import com.huayu.eframe.global.dict.flow.Dict;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/8/25.
 */
@Service
public class DictEntityCacheImpl extends AbstractFrameCache<DictEntityCacheDetail> implements DictEntityCache
{
    @Autowired
    private DictEntityAtom dictEntityAtom;

    @Autowired
    private DictParamEntityAtom dictParamEntityAtom;

    static  class DictEntityCacheDictCodeIndex implements Index<DictEntityCacheDetail>
    {
        @Override
        public String getIndex(DictEntityCacheDetail dictEntityCacheDetail)
        {
            return dictEntityCacheDetail.getDictCode();
        }
    }

    public DictEntityCacheImpl()
    {
        registerIndex(new DictEntityCacheDictCodeIndex());
    }

    @Override
    public List<Dict> queryDictByDictCode(String code)
    {
        DictEntityCacheDetail dictEntityCacheDetail = new DictEntityCacheDetail();
        dictEntityCacheDetail.setDictCode(code);
        List<DictEntityCacheDetail> langValues = getResultByIndex(DictEntityCacheDictCodeIndex.class, dictEntityCacheDetail);
        DictEntityCacheDetail dict = CollectionUtils.getFirstElement(langValues);
        return null == dict ? null : dict.getDicts();
    }

    @Override
    public List<DictEntityCacheDetail> load()
    {
        List<DictEntity> dictEntities = dictEntityAtom.queryAll();
        List<DictParamEntity> dictParamEntity = dictParamEntityAtom.queryAll();
        Map<String, List<Dict>> paramBindMap = bindMap(dictParamEntity);
        List<DictEntityCacheDetail> allDictEntity = new ArrayList<>();
        CollectionUtils.iterator(dictEntities,(c,v,i)->{
            DictEntityCacheDetail dictEntityCacheDetail = new DictEntityCacheDetail();
            List<Dict> list = paramBindMap.get(v.getDictCode());
            dictEntityCacheDetail.setDictCode(v.getDictCode());
            dictEntityCacheDetail.setDicts(null == list ? new ArrayList<>():list);
            allDictEntity.add(dictEntityCacheDetail);
        });
        return allDictEntity;
    }

    private Map<String, List<Dict>> bindMap(List<DictParamEntity> dictParamEntity )
    {
        Map<String, List<Dict>> forMap = new HashMap<>();
        CollectionUtils.iterator(dictParamEntity,(c,v,i)->{
            if(null !=v && null != v.getDictEntity() && StringUtils.isNotNullAndEmpty(v.getDictEntity().getDictCode()))
            {
                List<Dict> dictParamEntities = forMap.get(v.getDictEntity().getDictCode());
                if(null == dictParamEntities)
                {
                    dictParamEntities = new ArrayList<>();
                    forMap.put(v.getDictEntity().getDictCode(),dictParamEntities);
                }
                Dict dict = new Dict();
                dict.setKey(v.getKey());
                dict.setName(v.getValue());
                dictParamEntities.add(dict);

            }
        });
        return forMap;
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }
}
