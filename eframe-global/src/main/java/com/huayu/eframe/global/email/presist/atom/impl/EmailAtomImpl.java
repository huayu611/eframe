package com.huayu.eframe.global.email.presist.atom.impl;

import com.huayu.eframe.global.email.presist.atom.EmailAtom;
import com.huayu.eframe.global.email.presist.bo.EmailBO;
import com.huayu.eframe.global.email.presist.repository.EmailRepository;
import com.huayu.eframe.server.common.FramePaging;
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
 * Created by Leo on 2018/9/28.
 */
@Service
public class EmailAtomImpl implements EmailAtom
{
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public EmailBO insert(EmailBO emailBO)
    {
        return emailRepository.save(emailBO);
    }

    @Override
    public EmailBO update(EmailBO emailBO)
    {
        return emailRepository.save(emailBO);
    }

    @Override
    public EmailBO queryEmailByCode(String code)
    {
        EmailBO emailBO = new EmailBO();
        emailBO.setEmailCode(code);
        Specification<EmailBO> querySpecific = buildSpecification(emailBO);
        List<EmailBO> resultList = this.emailRepository.findAll(querySpecific);
        return CollectionUtils.getFirstElement(resultList);
    }

    @Override
    public Page<EmailBO> queryEmailByPage(FramePaging fp, EmailBO email)
    {
        return queryByPageCondition(fp,email);
    }

    @Override
    public List<EmailBO> getAllEmail()
    {
        return emailRepository.findAll();
    }

    @Override
    public List<EmailBO> getAllValidEmail()
    {
        EmailBO emailBO = new EmailBO();
        emailBO.setStatus("2");
        Specification<EmailBO> querySpecific = buildSpecification(emailBO);
        List<EmailBO> resultList = emailRepository.findAll(querySpecific);
        return resultList;
    }

    @Override
    public void delete(EmailBO emailBO)
    {
         emailRepository.delete(emailBO);
    }

    private Specification<EmailBO> buildSpecification(EmailBO condition)
    {
        return (Specification<EmailBO>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();

            if(null != condition.getName())
            {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%"));
            }
            if(null != condition.getEmailCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("emailCode").as(String.class),  condition.getEmailCode()));
            }
            if(null != condition.getStatus())
            {
                predicates.add(criteriaBuilder.equal(root.get("status").as(String.class),  condition.getStatus()));
            }
            predicates.add(criteriaBuilder.notEqual(root.get("status").as(String.class),"D"));;
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private Page<EmailBO> queryByPageCondition(FramePaging fp, EmailBO condition)
    {

        Sort sort = new Sort(Sort.Direction.ASC,"createTime");
        PageRequest pageRequest = PageRequest.of(fp.getPage(),fp.getSize(),sort);

        Specification<EmailBO> querySpecific = buildSpecification(condition);
        Page<EmailBO> resultList = this.emailRepository.findAll(querySpecific, pageRequest);
        return resultList;
    }
}
