package com.huayu.eframe.feedback.atom.impl;

import com.huayu.eframe.feedback.atom.FeedBackAtom;
import com.huayu.eframe.feedback.bo.FeedBackBO;
import com.huayu.eframe.feedback.repository.FeedBackRepository;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.sun.jndi.toolkit.dir.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * Created by Leo on 2018/11/24.
 */
@Service
public class FeedBackAtomImpl implements FeedBackAtom
{
    private static final LogDebug debug = new LogDebug(FeedBackAtomImpl.class);

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public FeedBackBO insert(FeedBackBO backBO)
    {

        return feedBackRepository.save(backBO);
    }

    @Override
    public List<FeedBackBO> queryAllFeedBack()
    {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return feedBackRepository.findAll(sort);
    }

    @Override
    public Page<FeedBackBO> queryFeedBackByPage(FramePaging fp)
    {

        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(),fp.getSize(),sort);
        Page<FeedBackBO> result = feedBackRepository.findAll(pageRequest);
        return result;
    }

    @Override
    public Page<FeedBackBO> queryFeedBackByPageAndDateAndContent(FramePaging fp, Date start, Date end, String content)
    {
        debug.log(DateUtils.dateToString(start));
        debug.log(DateUtils.dateToString(end));
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(),fp.getSize(),sort);

        Specification<FeedBackBO> querySpecifi = new Specification<FeedBackBO>(){

            @Override
            public Predicate toPredicate(Root<FeedBackBO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                List<Predicate> predicates = new ArrayList<>();
                if(null != start)
                {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class),start));
                }
                if(null != end)
                {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class),end));
                }
                if(StringUtils.isNotNullAndEmpty(content))
                {
                    predicates.add(criteriaBuilder.like(root.get("fdStaffContent").as(String.class), "%" + content + "%"));
                }
                debug.log(predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
        };
        Page<FeedBackBO> resultList = this.feedBackRepository.findAll(querySpecifi, pageRequest);
        debug.log(resultList.getContent().size());
        return resultList;
    }

    @Override
    public void deleteFeedBackById(Long id)
    {
        FeedBackBO fb = new FeedBackBO();
        fb.setId(id);
        feedBackRepository.delete(fb);
    }
}
