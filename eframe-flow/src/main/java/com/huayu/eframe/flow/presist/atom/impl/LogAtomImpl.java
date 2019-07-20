package com.huayu.eframe.flow.presist.atom.impl;

import com.huayu.eframe.flow.presist.atom.LogAtom;
import com.huayu.eframe.flow.presist.bo.LogEntity;
import com.huayu.eframe.flow.presist.repository.LogRepository;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Leo on 2019/2/13.
 */
@Service
public class LogAtomImpl implements LogAtom
{

    @Autowired
    private LogRepository logRepository;

    @Override
    public LogEntity addLog(LogEntity logEntity)
    {
        return logRepository.save(logEntity);
    }

    @Override
    public LogEntity updateLog(LogEntity logEntity)
    {
        return logRepository.save(logEntity);
    }

    @Override
    public LogEntity getLogByCode(String logCode)
    {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogCode(logCode);

        ExampleMatcher em = ExampleMatcher.matching();
        Example<LogEntity> example = Example.of(logEntity, em);

        List<LogEntity> logEntities = logRepository.findAll(example);
        return CollectionUtils.getFirstElement(logEntities);
    }

    @Override
    public LogEntity getLogById(Long id)
    {
        Optional<LogEntity> result = logRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Page<LogEntity> queryLog(FramePaging fp, LogEntity condition)
    {
        Sort sort = new Sort(Sort.Direction.DESC, "inTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(), fp.getSize(), sort);
        Specification<LogEntity> querySpecific = new Specification<LogEntity>()
        {

            @Override
            public Predicate toPredicate(Root<LogEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if (null != condition && null != condition.getInTime())
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("inTime").as(Date.class), condition.getInTime()));
                }
                if (null != condition && null != condition.getOutTime())
                {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("outTime").as(Date.class), condition.getOutTime()));
                }
                if (null != condition && null != condition.getMethod())
                {
                    predicates.add(criteriaBuilder.equal(root.get("method").as(String.class), condition.getMethod()));
                }
                if (null != condition && null != condition.getUrl())
                {
                    predicates.add(criteriaBuilder.like(root.get("url").as(String.class), "%" + condition.getUrl() + "%"));
                }
                if (null != condition && null != condition.getStatus())
                {
                    predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), condition.getStatus()));
                }
                if (null != condition && null != condition.getOperObjType())
                {
                    predicates.add(criteriaBuilder.equal(root.get("operObjType").as(String.class), condition.getOperObjType()));
                }
                if (null != condition && null != condition.getOperObjId())
                {
                    predicates.add(criteriaBuilder.equal(root.get("operObjId").as(Long.class), condition.getOperObjId()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

            ;
        };
        Page<LogEntity> resultList = this.logRepository.findAll(querySpecific, pageRequest);
        return resultList;
    }


    @Override
    public Page<LogEntity> queryWillDeleteLog(FramePaging fp, Date deletionTime)
    {
        Sort sort = new Sort(Sort.Direction.ASC, "inTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(), fp.getSize(), sort);
        Specification<LogEntity> querySpecific = (Specification<LogEntity>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), deletionTime));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<LogEntity> resultList = this.logRepository.findAll(querySpecific, pageRequest);
        return resultList;
    }


    @Override
    public void remove(List<LogEntity> logEntities)
    {
        logRepository.deleteAll(logEntities);
    }
}
