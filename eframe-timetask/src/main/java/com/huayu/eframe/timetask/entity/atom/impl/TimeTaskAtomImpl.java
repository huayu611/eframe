package com.huayu.eframe.timetask.entity.atom.impl;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import com.huayu.eframe.timetask.entity.atom.TimeTaskAtom;
import com.huayu.eframe.timetask.entity.bo.TimeTaskBO;
import com.huayu.eframe.timetask.entity.repository.TimeTaskRepository;
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
import java.util.Optional;

/**
 * Created by Leo on 2019/4/15.
 */
@Service
public class TimeTaskAtomImpl implements TimeTaskAtom
{
    @Autowired
    private TimeTaskRepository timeTaskRepository;

    @Override
    public TimeTaskBO addTimeTask(TimeTaskBO timeTaskBO)
    {
        return timeTaskRepository.save(timeTaskBO);
    }

    @Override
    public TimeTaskBO updateTimeTask(TimeTaskBO timeTaskBO)
    {
        return timeTaskRepository.save(timeTaskBO);
    }

    @Override
    public TimeTaskBO queryTimeTaskByCode(String code)
    {
        if(StringUtils.isNullOrEmpty(code))
        {
            return null;
        }
        TimeTaskBO timeTaskBO = new TimeTaskBO();
        timeTaskBO.setCode(code);
        Specification<TimeTaskBO> specification = buildSpecification(timeTaskBO);

        List<TimeTaskBO> timeTaskList = timeTaskRepository.findAll(specification);
        return CollectionUtils.getFirstElement(timeTaskList);
    }

    @Override
    public  Page<TimeTaskBO> queryTimeTaskByPage(TimeTaskBO timeTaskBO, FramePaging fp)
    {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(),fp.getSize(),sort);

        Specification<TimeTaskBO> querySpecific = buildSpecification(timeTaskBO);
        Page<TimeTaskBO> resultList = this.timeTaskRepository.findAll(querySpecific, pageRequest);
        return resultList;
    }

    @Override
    public List<TimeTaskBO> queryAllTimeTask()
    {
        TimeTaskBO timeTaskBO = new TimeTaskBO();
        Specification<TimeTaskBO> specification = buildSpecification(timeTaskBO);
        List<TimeTaskBO> timeTaskList = timeTaskRepository.findAll(specification);
        return timeTaskList;
    }

    @Override
    public void updateTimeTaskAddExecuteTime(Long id,Date date)
    {
        timeTaskRepository.updateTimeTaskAddExecuteCount(id,date);
    }



    private Specification<TimeTaskBO> buildSpecification(TimeTaskBO condition)
    {
        return (Specification<TimeTaskBO>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();

            if(null != condition.getName())
            {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%"));
            }
            if(null != condition.getCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("code").as(String.class),  condition.getCode()));
            }
            if(null != condition.getStatus())
            {
                predicates.add(criteriaBuilder.equal(root.get("status").as(String.class),  condition.getStatus()));
            }
            predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class),"D"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
