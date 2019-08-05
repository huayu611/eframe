package com.huayu.eframe.config.lang.entity.atom.impl;

import com.huayu.eframe.config.lang.entity.atom.LangTextAtom;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import com.huayu.eframe.config.lang.entity.bo.LangText;
import com.huayu.eframe.config.lang.entity.repository.LangTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/8/3.
 */
@Service
public class LangTextAtomImpl implements LangTextAtom
{
    @Autowired
    private LangTextRepository langTextRepository;

    @Override
    public List<LangText> batchAddLangText(List<LangText> lang)
    {
        return langTextRepository.saveAll(lang);
    }

    @Override
    public List<LangText> batchUpdateLangText(List<LangText> lang)
    {
        return langTextRepository.saveAll(lang);
    }

    @Override
    public List<LangText> queryLangTextByDefine(LangDefine langDefine)
    {
        LangText lang = new LangText();
        lang.setLangDefine(langDefine);
        Specification<LangText> specification = buildSpecification(lang);
        return langTextRepository.findAll(specification);
    }

    @Override
    public List<LangText> queryLangTextByCode(String fCode)
    {
        LangText lang = new LangText();
        lang.setLangCode(fCode);
        Specification<LangText> specification = buildSpecification(lang);
        return langTextRepository.findAll(specification);
    }

    @Override
    public List<LangText> queryLangText()
    {
        LangText lang = new LangText();
        Specification<LangText> specification = buildSpecification(lang);
        return langTextRepository.findAll(specification);
    }

    @Override
    public void batchRemoveLangValue(List<LangText> lang)
    {
        langTextRepository.deleteInBatch(lang);
    }

    private Specification<LangText> buildSpecification(LangText condition)
    {
        return (Specification<LangText>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if(null != condition && null != condition.getLangDefine())
            {
                predicates.add(criteriaBuilder.equal(root.get("langDefine").as(LangDefine.class), condition.getLangDefine() ));
            }
            if(null != condition && null != condition.getPrimaryCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("primaryCode").as(String.class), condition.getPrimaryCode() ));
            }
            if(null != condition && null != condition.getLangCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("langCode").as(String.class), condition.getLangCode() ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
