package com.huayu.eframe.server.security.service.atom.impl;

import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.security.service.atom.StaffAtom;
import com.huayu.eframe.server.security.service.bo.Staff;
import com.huayu.eframe.server.security.service.repository.StaffRepository;
import com.huayu.eframe.server.tool.basic.DateUtils;
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

/**
 * Created by Leo on 2018/9/16.
 */
@Service
public class StaffAtomImpl implements StaffAtom
{

    private static final LogDebug debug = new LogDebug(StaffAtomImpl.class);

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Staff insert(Staff staff)
    {
        return staffRepository.save(staff);
    }

    @Override
    public Staff update(Staff staff)
    {
        return staffRepository.save(staff);
    }

    @Override
    public void delete(Staff staff)
    {
        staffRepository.delete(staff);
    }

    @Override
    public List<Staff> queryStaff(Staff staff)
    {
        ExampleMatcher em = ExampleMatcher.matching();
        Example<Staff> example = Example.of(staff, em);

        List<Staff> staffList = staffRepository.findAll(example);
        return staffList;
    }

    @Override
    public List<Staff> queryStaffByLogin(String login,Date now)
    {
        Staff condition = new Staff();
        condition.setLoginName(login);
        return queryStaffByCondition(condition,now);
    }

    @Override
    public Page<Staff> queryValidStaffByPage(FramePaging fp, Date now, Staff condition)
    {
        debug.log(DateUtils.dateToString(now));
        Sort sort = new Sort(Sort.Direction.DESC, "staffname");
        PageRequest pageRequest = PageRequest.of(fp.getPage(), fp.getSize(), sort);

        Specification<Staff> querySpecific = new Specification<Staff>()
        {

            @Override
            public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if (null != now)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class), now));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("effectiveTime").as(Date.class), now));
                }
                if (null != condition.getLoginName())
                {
                    predicates.add(criteriaBuilder.like(root.get("loginName").as(String.class), "%" + condition.getLoginName() + "%"));
                }
                if (null != condition.getStaffname())
                {
                    predicates.add(criteriaBuilder.like(root.get("staffname").as(String.class), "%" + condition.getStaffname() + "%"));
                }
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

            ;
        };
        Page<Staff> resultList = this.staffRepository.findAll(querySpecific, pageRequest);
        debug.log(resultList.getContent().size());
        return resultList;
    }

    private List<Staff> queryStaffByCondition(Staff condition, Date now)
    {
        Specification<Staff> querySpecific = new Specification<Staff>()
        {
            @Override
            public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if (null != now)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("expireTime").as(Date.class), now));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("effectiveTime").as(Date.class), now));
                }
                if (null != condition.getLoginName())
                {
                    predicates.add(criteriaBuilder.equal(root.get("loginName").as(String.class), condition.getLoginName()));
                }
                if (null != condition.getStaffname())
                {
                    predicates.add(criteriaBuilder.equal(root.get("staffname").as(String.class), condition.getStaffname()));
                }
                predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class), "D"));
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

            ;
        };
        List<Staff> resultList = this.staffRepository.findAll(querySpecific);
        return resultList;
    }
}
