package com.huayu.eframe.timetask.entity.atom.impl;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.timetask.entity.atom.TimeTaskInstanceAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskInstance;
import com.huayu.eframe.timetask.entity.repository.TimeTaskInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskInstanceAtomImpl implements TimeTaskInstanceAtom
{

    @Autowired
    private TimeTaskInstanceRepository timeTaskInstanceRepository;

    @Override
    public TimeTaskInstance addTimeTaskInstance(TimeTaskInstance timeTaskInstance)
    {
        return timeTaskInstanceRepository.save(timeTaskInstance);
    }

    @Override
    public TimeTaskInstance updateTimeTaskInstance(TimeTaskInstance timeTaskInstance)
    {
        return timeTaskInstanceRepository.save(timeTaskInstance);
    }

    @Override
    public Page<TimeTaskInstance> queryTimeTaskInstanceByPage(TimeTaskInstance timeTaskInstance, FramePaging fp)
    {

        Specification<TimeTaskInstance> special = buildSpecification(timeTaskInstance);
        Sort sort = new Sort(Sort.Direction.DESC, "startTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(), fp.getSize(), sort);
        return timeTaskInstanceRepository.findAll(special, pageRequest);
    }

    @Override
    public Page<TimeTaskInstance> queryWillDeleteTaskInstanceByPage(FramePaging fp, Date deletionTime)
    {
        Sort sort = new Sort(Sort.Direction.ASC, "endTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(), fp.getSize(), sort);
        Specification<TimeTaskInstance> querySpecific = (Specification<TimeTaskInstance>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime").as(Date.class), deletionTime));
            predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), "2"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<TimeTaskInstance> resultList = timeTaskInstanceRepository.findAll(querySpecific, pageRequest);
        return resultList;
    }

    @Override
    public void batchDeleteTaskInstance(List<TimeTaskInstance> timeTaskInstances)
    {
        timeTaskInstanceRepository.deleteInBatch(timeTaskInstances);
    }

    private Specification<TimeTaskInstance> buildSpecification(TimeTaskInstance condition)
    {
        return (Specification<TimeTaskInstance>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (null != condition.getTimeTaskId())
            {
                predicates.add(criteriaBuilder.equal(root.get("timeTaskId").as(Long.class), condition.getTimeTaskId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


}
