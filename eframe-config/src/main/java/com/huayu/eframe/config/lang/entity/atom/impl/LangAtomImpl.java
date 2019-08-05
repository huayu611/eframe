package com.huayu.eframe.config.lang.entity.atom.impl;

import com.huayu.eframe.config.lang.entity.atom.LangAtom;
import com.huayu.eframe.config.lang.entity.bo.LangValue;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import com.huayu.eframe.config.lang.entity.repository.LangRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
@Service
public class LangAtomImpl implements LangAtom
{

    @Autowired
    private LangRepository langRepository;


    @Override
    public LangValue addLangValue(LangValue lang)
    {
        return langRepository.save(lang);
    }

    @Override
    public List<LangValue> batchAddLangValue(List<LangValue> lang)
    {
        return langRepository.saveAll(lang);
    }

    @Override
    public List<LangValue> batchUpdateLangValue(List<LangValue> lang)
    {
        return langRepository.saveAll(lang);
    }

    @Override
    public List<LangValue> queryLangValueByLang(LangDefine langDefine)
    {
        LangValue lang = new LangValue();
        lang.setLangDefine(langDefine);
        Specification<LangValue> specification = buildSpecification(lang);
        return langRepository.findAll(specification);
    }

    @Override
    public List<LangValue> queryLangValueByCode(String fCode)
    {
        LangValue lang = new LangValue();
        lang.setLangCode(fCode);
        Specification<LangValue> specification = buildSpecification(lang);
        return langRepository.findAll(specification);
    }

    @Override
    public LangValue queryLangValueByLangAndCode(LangDefine langDefine, String fCode)
    {
        LangValue lang = new LangValue();
        lang.setLangCode(fCode);
        lang.setLangDefine(langDefine);
        Specification<LangValue> specification = buildSpecification(lang);
        List<LangValue> result = langRepository.findAll(specification);
        return CollectionUtils.getFirstElement(result);
    }

    @Override
    public LangValue queryLangValueByPrimaryCode(String code)
    {
        LangValue lang = new LangValue();
        lang.setPrimaryCode(code);
        Specification<LangValue> specification = buildSpecification(lang);
        List<LangValue> result = langRepository.findAll(specification);
        return CollectionUtils.getFirstElement(result);
    }

    @Override
    public LangValue updateLangValue(LangValue lang)
    {
        return langRepository.save(lang);
    }

    @Override
    public void removeLangValue(LangValue lang)
    {
        langRepository.delete(lang);
    }

    @Override
    public void batchRemoveLangValue(List<LangValue> lang)
    {
        langRepository.deleteInBatch(lang);
    }

    @Override
    public List<LangValue> queryLangValue()
    {
        return langRepository.findAll();
    }

    private Specification<LangValue> buildSpecification(LangValue condition)
    {
        return (Specification<LangValue>) (root, criteriaQuery, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if(null != condition && null != condition.getLangDefine())
            {
                predicates.add(criteriaBuilder.equal(root.get("langDefine").as(LangDefine.class), condition.getLangDefine() ));
            }
            if(null != condition && null != condition.getLangCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("langCode").as(String.class), condition.getLangCode() ));
            }
            if(null != condition && null != condition.getPrimaryCode())
            {
                predicates.add(criteriaBuilder.equal(root.get("primaryCode").as(String.class), condition.getPrimaryCode() ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
