package com.huayu.eframe.timetask.entity.atom.impl;

import com.huayu.eframe.timetask.entity.atom.AsynchronousOrderAtom;
import com.huayu.eframe.timetask.entity.bo.AsynchronousOrder;
import com.huayu.eframe.timetask.entity.repository.AsynchronousOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2020/10/30.
 */
@Service
public class AsynchronousOrderAtomImpl implements AsynchronousOrderAtom
{

    @Autowired
    private AsynchronousOrderRepository asynchronousOrderRepository;

    @Override
    public AsynchronousOrder saveAsynchronousOrder(AsynchronousOrder asynchronousOrder)
    {
        return asynchronousOrderRepository.save(asynchronousOrder);
    }

    @Override
    public List<AsynchronousOrder> queryNeedExecuteOrder(AsynchronousOrder asynchronousOrder, Date now)
    {
        if(null == asynchronousOrder)
        {
            asynchronousOrder = new AsynchronousOrder();
        }
        Specification<AsynchronousOrder> special = buildSpecification(asynchronousOrder,now);
        return asynchronousOrderRepository.findAll(special);
    }

    private Specification<AsynchronousOrder> buildSpecification(AsynchronousOrder condition,Date now)
    {
        return (Specification<AsynchronousOrder>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();


            if (null != condition.getId())
            {
                predicates.add(criteriaBuilder.equal(root.get("id").as(Long.class), condition.getId()));
            }
            if (null != condition.getStatus())
            {
                predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), condition.getStatus()));
            }
            if (null != now)
            {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("nextExecuteTimes").as(Date.class), now));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
