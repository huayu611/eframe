package com.huayu.eframe.global.dict.entity.atom.impl;

import com.huayu.eframe.global.dict.entity.atom.DictEntityAtom;
import com.huayu.eframe.global.dict.entity.bo.DictEntity;
import com.huayu.eframe.global.dict.entity.repository.DictEntityRepository;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class DictEntityAtomImpl implements DictEntityAtom
{
    @Autowired
    private DictEntityRepository dictEntityRepository;

    @Override
    public DictEntity addDictEntity(DictEntity dictEntity)
    {
        return dictEntityRepository.save(dictEntity);
    }

    @Override
    public DictEntity updateDictEntity(DictEntity dictEntity)
    {
        return dictEntityRepository.save(dictEntity);
    }

    @Override
    public DictEntity queryDictEntityByInnerCode(String innerCode)
    {
        DictEntity dictEntity = new DictEntity();
        dictEntity.setCode(innerCode);
        Specification<DictEntity> specification = buildSpecification(dictEntity);
        List<DictEntity> dictEntities = dictEntityRepository.findAll(specification);
        return CollectionUtils.getFirstElement(dictEntities);
    }

    @Override
    public List<DictEntity> queryDictEntityByDictCode(String dictCode)
    {
        DictEntity dictEntity = new DictEntity();
        dictEntity.setDictCode(dictCode);
        Specification<DictEntity> specification = buildSpecification(dictEntity);
       return dictEntityRepository.findAll(specification);

    }

    @Override
    public Page<DictEntity> queryDictEntityByCode(FramePaging framePaging,DictEntity dictEntity)
    {
        Specification<DictEntity> specification = buildSpecification(dictEntity);
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(framePaging.getPage(), framePaging.getSize(), sort);
        return dictEntityRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<DictEntity> queryAll()
    {
        DictEntity dictEntity = new DictEntity();
        Specification<DictEntity> specification = buildSpecification(dictEntity);
        return  dictEntityRepository.findAll(specification);
    }

    private Specification<DictEntity> buildSpecification(DictEntity condition)
    {
        return (Specification<DictEntity>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if(null != condition && null != condition.getCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("code").as(String.class), condition.getCode() ));
            }
            if(null != condition && null != condition.getDictCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("dictCode").as(String.class), condition.getDictCode() ));
            }
            if(null != condition && null != condition.getDictName())
            {
                predicates.add(criteriaBuilder.like(root.get("dictName").as(String.class), StringUtils.getLikeString(condition.getDictName())));
            }
            predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class),"D"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
