package com.huayu.eframe.global.dict.entity.service.impl;

import com.huayu.eframe.global.dict.entity.atom.DictEntityAtom;
import com.huayu.eframe.global.dict.entity.atom.DictParamEntityAtom;
import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import com.huayu.eframe.global.dict.entity.bo.DictParamEntity;
import com.huayu.eframe.global.dict.entity.cache.DictEntityCache;
import com.huayu.eframe.global.dict.entity.service.DictEntityDetail;
import com.huayu.eframe.global.dict.entity.service.DictEntityService;
import com.huayu.eframe.global.dict.entity.service.DictParamDetail;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/8/24.
 */
@Service
public class DictEntityServiceImpl implements DictEntityService
{
    @Autowired
    private DictEntityAtom dictEntityAtom;

    @Autowired
    private DictParamEntityAtom dictParamEntityAtom;

    @Override
    public DictEntityDetail addDictEntity(DictEntityDetail dictEntityDetail)
    {
        List<DictEntity> dictEntity = queryDictEntityByDictCode(dictEntityDetail.getDictCode());
        if(CollectionUtils.isNotEmpty(dictEntity))
        {
            throw new IFPException();
        }
        DictEntity dict = buildDictEntity(dictEntityDetail);
        DictEntity result = dictEntityAtom.addDictEntity(dict);
        LocalAttribute.addNeedRefreshCache(DictEntityCache.CACHE_NAME);
        return buildDictEntityDetail(result);
    }

    @Override
    public DictEntityDetail updateDictEntity(DictEntityDetail dictEntityDetail)
    {
        DictEntity dictEntity = queryDictEntityByInnerCode(dictEntityDetail.getDictInnerCode());
        if(null == dictEntity)
        {
            throw new IFPException();
        }
        putInformation(dictEntity,dictEntityDetail);
        DictEntity result = dictEntityAtom.addDictEntity(dictEntity);
        LocalAttribute.addNeedRefreshCache(DictEntityCache.CACHE_NAME);
        return buildDictEntityDetail(result);

    }

    @Override
    public String removeDictEntity(String innerCode)
    {
        DictEntity dictEntity = queryDictEntityByInnerCode(innerCode);
        if(null == dictEntity)
        {
            throw new IFPException();
        }
        dictEntity.setStatus("D");
        DictEntity result = dictEntityAtom.addDictEntity(dictEntity);
        LocalAttribute.addNeedRefreshCache(DictEntityCache.CACHE_NAME);
        return null == result? "" : result.getCode();
    }

    @Override
    public PageObject queryDictEntity(PagingRequest pagingRequest, DictEntityDetail dictEntityDetail)
    {
        FramePaging framePaging = new FramePaging();
        if (null != pagingRequest)
        {
            framePaging.setPage(pagingRequest.getPage());
            framePaging.setSize(pagingRequest.getSize());
        }
        DictEntity dictEntity = new DictEntity();
        putInformation(dictEntity,dictEntityDetail);
        Page<DictEntity> viewPage = dictEntityAtom.queryDictEntityByCode(framePaging, dictEntity);
        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(viewPage.getTotalElements());
        pagingResponse.setCurrentPage(viewPage.getNumber());
        pagingResponse.setTotalPage(viewPage.getTotalPages());
        PageObject pageObject = new PageObject();
        pageObject.setPagingResponse(pagingResponse);
        pageObject.setResponse(buildDictEntityDetails(viewPage.getContent()));
        return pageObject;
    }

    @Override
    public DictParamDetail addDictParam(DictParamDetail dictParamDetail)
    {
        DictParamEntity dictParam = buildDictParamEntity(dictParamDetail);
        DictParamEntity dictParamEntityExist = queryDictParamEntityByKeyAndDict(dictParam.getKey(), dictParam.getDictEntity());
        if(null != dictParamEntityExist)
        {
            throw new IFPException();
        }
        DictParamEntity result = dictParamEntityAtom.addDictParamEntity(dictParam);
        LocalAttribute.addNeedRefreshCache(DictEntityCache.CACHE_NAME);
        return buildDictParamDetail(result);
    }

    @Override
    public DictParamDetail updateDictParam(DictParamDetail dictParamDetail)
    {
        DictParamEntity dictParamEntityExist = queryDictParamEntityByCode(dictParamDetail.getCode());
        if(null == dictParamEntityExist)
        {
            throw new IFPException();
        }
        putDictParamEntity(dictParamEntityExist,dictParamDetail);
        DictParamEntity result = dictParamEntityAtom.addDictParamEntity(dictParamEntityExist);
        LocalAttribute.addNeedRefreshCache(DictEntityCache.CACHE_NAME);
        return buildDictParamDetail(result);
    }

    @Override
    public String removeDictParam(String code)
    {
        DictParamEntity dictParamEntity = queryDictParamEntityByCode(code);
        if(null == dictParamEntity)
        {
            throw new IFPException();
        }
        dictParamEntityAtom.removeDictParamEntity(dictParamEntity);
        LocalAttribute.addNeedRefreshCache(DictEntityCache.CACHE_NAME);
        return code;
    }

    @Override
    public List<DictParamDetail> queryDictParamByDictEntity(String dictInnerDictDode)
    {
        DictEntity innerDict = queryDictEntityByInnerCode(dictInnerDictDode);
        if(null == innerDict)
        {
            return null;
        }
        List<DictParamEntity> dictParamList = dictParamEntityAtom.queryDictParamByDict(innerDict);
        return buildDictParamDetail(dictParamList);
    }

    private DictEntity queryDictEntityByInnerCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            throw new IFPException();
        }
        return dictEntityAtom.queryDictEntityByInnerCode(code);
    }

    private List<DictEntity> queryDictEntityByDictCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            throw new IFPException();
        }
        return dictEntityAtom.queryDictEntityByDictCode(code);
    }

    private DictEntity buildDictEntity(DictEntityDetail dictEntityDetail)
    {
        DictEntity dictEntity = new DictEntity();
        dictEntity.setCode(dictEntityDetail.getDictInnerCode());
        putInformation(dictEntity,dictEntityDetail);
        dictEntity.setStatus("2");
        dictEntity.setCreateTime(LocalAttribute.getNow());
        return dictEntity;
    }
    private void putInformation(DictEntity dictEntity,DictEntityDetail dictEntityDetail)
    {
        if(null == dictEntity || null == dictEntityDetail)
        {
            return ;
        }
        if(null != dictEntityDetail.getDictCode())
        {
            dictEntity.setDictCode(dictEntityDetail.getDictCode());
        }
        if(null != dictEntityDetail.getDictName())
        {
            dictEntity.setDictName(dictEntityDetail.getDictName());
        }
    }

    private List<DictEntityDetail> buildDictEntityDetails(List<DictEntity> dictEntity)
    {
        List<DictEntityDetail> result = new ArrayList<>();
        CollectionUtils.iterator(dictEntity,(c,v,i)->{
            DictEntityDetail d = buildDictEntityDetail(v);
            if(null !=d)
            {
                result.add(d);
            }
        });
        return result;
    }

    private DictEntityDetail buildDictEntityDetail(DictEntity dictEntity)
    {
        if(null == dictEntity)
        {
            return null;
        }
        DictEntityDetail dictEntityDetail = new DictEntityDetail();
        dictEntityDetail.setDictCode(dictEntity.getDictCode());
        dictEntityDetail.setDictName(dictEntity.getDictName());
        dictEntityDetail.setDictInnerCode(dictEntity.getCode());
        return dictEntityDetail;
    }

    private DictParamEntity buildDictParamEntity(DictParamDetail dictParamDetail)
    {
        DictParamEntity dictParamEntity = new DictParamEntity();
        putDictParamEntity(dictParamEntity,dictParamDetail);
        DictEntity dictEntity = queryDictEntityByInnerCode(dictParamDetail.getDictInnerCode());
        if(null == dictEntity)
        {
            throw new IFPException();
        }
        dictParamEntity.setCode(RandomUtils.getRandomUUID());
        dictParamEntity.setDictEntity(dictEntity);
        return dictParamEntity;
    }
    private void putDictParamEntity(DictParamEntity dictParamEntity,DictParamDetail dictParamDetail)
    {
        if(null == dictParamDetail || null == dictParamEntity)
        {
            return;
        }
        if(null != dictParamDetail.getOrder())
        {
            dictParamEntity.setOrder(dictParamDetail.getOrder());
        }
        if(null != dictParamDetail.getValue())
        {
            dictParamEntity.setValue(dictParamDetail.getValue());
        }
        if(null != dictParamDetail.getKey())
        {
            dictParamEntity.setKey(dictParamDetail.getKey());
        }
    }

    private List<DictParamDetail> buildDictParamDetail(List<DictParamEntity> dictParamEntities)
    {
        List<DictParamDetail> result = new ArrayList<>();
        CollectionUtils.iterator(dictParamEntities,(c,v,i)->{
            DictParamDetail dictParamDetail = buildDictParamDetail(v);
            if(null != dictParamDetail)
            {
                result.add(dictParamDetail);
            }
        });
        return result;
    }

    private DictParamDetail buildDictParamDetail(DictParamEntity dictParamEntity)
    {
        if(null == dictParamEntity)
        {
            return null;
        }
        DictParamDetail dictParamDetail = new DictParamDetail();
        if(null != dictParamEntity.getDictEntity())
        {
            dictParamDetail.setDictCode(dictParamEntity.getDictEntity().getDictCode());
            dictParamDetail.setDictInnerCode(dictParamEntity.getDictEntity().getCode());
        }
        dictParamDetail.setCode(dictParamEntity.getCode());
        dictParamDetail.setKey(dictParamEntity.getKey());
        dictParamDetail.setOrder(dictParamEntity.getOrder());
        dictParamDetail.setValue(dictParamEntity.getValue());
        return dictParamDetail;
    }

    private DictParamEntity queryDictParamEntityByCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            throw new IFPException();
        }
        return dictParamEntityAtom.queryDictParamByCode(code);
    }

    private DictParamEntity queryDictParamEntityByKeyAndDict(String key,DictEntity dictEntity)
    {
        if(StringUtils.isNullOrEmpty(key) || null == dictEntity)
        {
            throw new IFPException();
        }
        return dictParamEntityAtom.queryDictParamByEntityAndKey(dictEntity,key);
    }
}
