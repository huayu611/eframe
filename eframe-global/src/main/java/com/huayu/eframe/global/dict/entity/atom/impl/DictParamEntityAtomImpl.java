package com.huayu.eframe.global.dict.entity.atom.impl;

import com.huayu.eframe.global.dict.entity.atom.DictParamEntityAtom;
import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import com.huayu.eframe.global.dict.entity.bo.DictParamEntity;
import com.huayu.eframe.global.dict.entity.repository.DictParamEntityRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/8/24.
 */
@Service
public class DictParamEntityAtomImpl implements DictParamEntityAtom
{
    @Autowired
    private DictParamEntityRepository dictParamEntityRepository;

    @Override
    public DictParamEntity addDictParamEntity(DictParamEntity dictParamEntity)
    {
        return dictParamEntityRepository.save(dictParamEntity);
    }

    @Override
    public DictParamEntity updateDictParamEntity(DictParamEntity dictParamEntity)
    {
        return dictParamEntityRepository.save(dictParamEntity);
    }

    @Override
    public void removeDictParamEntity(DictParamEntity dictParamEntity)
    {
        dictParamEntityRepository.delete(dictParamEntity);
    }

    @Override
    public List<DictParamEntity> queryDictParamByDict(DictEntity dictEntity)
    {
        DictParamEntity dictParamEntity = new DictParamEntity();
        dictParamEntity.setDictEntity(dictEntity);
        Sort sort = new Sort(Sort.Direction.ASC, "order");
        Specification<DictParamEntity> specification = buildSpecification(dictParamEntity);
        return dictParamEntityRepository.findAll(specification,sort);

    }

    @Override
    public DictParamEntity queryDictParamByEntityAndKey(DictEntity dictEntity,String key)
    {
        DictParamEntity dictParamEntity = new DictParamEntity();
        dictParamEntity.setKey(key);
        dictParamEntity.setDictEntity(dictEntity);
        Specification<DictParamEntity> specification = buildSpecification(dictParamEntity);
        List<DictParamEntity> dictEntities = dictParamEntityRepository.findAll(specification);
        return CollectionUtils.getFirstElement(dictEntities);
    }

    @Override
    public DictParamEntity queryDictParamByCode(String code)
    {
        DictParamEntity dictParamEntity = new DictParamEntity();
        dictParamEntity.setCode(code);
        Specification<DictParamEntity> specification = buildSpecification(dictParamEntity);
        List<DictParamEntity> dictEntities = dictParamEntityRepository.findAll(specification);
        return CollectionUtils.getFirstElement(dictEntities);
    }

    @Override
    public List<DictParamEntity> queryAll()
    {
        return dictParamEntityRepository.findAll();
    }

    private Specification<DictParamEntity> buildSpecification(DictParamEntity condition)
    {
        return (Specification<DictParamEntity>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if(null != condition && null != condition.getCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("code").as(String.class), condition.getCode() ));
            }
            if(null != condition && null != condition.getKey())
            {
                predicates.add(criteriaBuilder.equal(root.get("key").as(String.class), condition.getKey() ));
            }
            if(null != condition && null != condition.getDictEntity())
            {
                predicates.add(criteriaBuilder.equal(root.get("dictEntity").as(DictEntity.class), condition.getDictEntity() ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
