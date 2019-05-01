package com.huayu.eframe.management.atom.impl;

import com.huayu.eframe.management.atom.StaffExtAtom;
import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.management.bo.StaffExt;
import com.huayu.eframe.management.repository.StaffExtRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Leo on 2019/4/22.
 */
@Service
public class StaffExtAtomImpl implements StaffExtAtom
{

    @Autowired
    private StaffExtRepository staffExtRepository;

    @Override
    public StaffExt addStaffExt(StaffExt staffExt)
    {
        return staffExtRepository.save(staffExt);
    }

    @Override
    public StaffExt updateStaffExt(StaffExt staffExt)
    {
        return staffExtRepository.save(staffExt);
    }

    @Override
    public StaffExt queryStaffExtByStaff(Staff staff)
    {
        StaffExt staffExt = new StaffExt();
        staffExt.setStaff(staff);
        Specification specification = buildSpecification(staffExt);
        List<StaffExt> queryResult = staffExtRepository.findAll(specification);
        return CollectionUtils.getFirstElement(queryResult);
    }

    @Override
    public StaffExt queryStaffExtById(Long id)
    {
        Optional<StaffExt> option = staffExtRepository.findById(id);
        return option.isPresent() ? option.get() : null;
    }

    @Override
    public void removeStaffExt(StaffExt staffExt)
    {
        staffExtRepository.delete(staffExt);
    }

    private Specification<StaffExt> buildSpecification(StaffExt condition)
    {
        return (Specification<StaffExt>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();

            if (null != condition.getStaff())
            {
                predicates.add(criteriaBuilder.equal(root.get("staff").as(Staff.class), condition.getStaff()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
