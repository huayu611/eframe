package com.huayu.eframe.server.common.i18n.table.atom.impl;

import com.huayu.eframe.server.common.i18n.table.atom.LangAtom;
import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.bo.LangDefine;
import com.huayu.eframe.server.common.i18n.table.repository.LangRepository;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
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
    public Lang addLangValue(Lang lang)
    {
        return langRepository.save(lang);
    }

    @Override
    public List<Lang> batchAddLangValue(List<Lang> lang)
    {
        return langRepository.saveAll(lang);
    }

    @Override
    public List<Lang> batchUpdateLangValue(List<Lang> lang)
    {
        return langRepository.saveAll(lang);
    }

    @Override
    public List<Lang> queryLangValueByLang(LangDefine langDefine)
    {
        Lang lang = new Lang();
        lang.setLangDefine(langDefine);
        Specification<Lang> specification = buildSpecification(lang);
        return langRepository.findAll(specification);
    }

    @Override
    public List<Lang> queryLangValueByCode(String fCode)
    {
        Lang lang = new Lang();
        lang.setLangCode(fCode);
        Specification<Lang> specification = buildSpecification(lang);
        return langRepository.findAll(specification);
    }

    @Override
    public Lang queryLangValueByLangAndCode(LangDefine langDefine, String fCode)
    {
        Lang lang = new Lang();
        lang.setLangCode(fCode);
        lang.setLangDefine(langDefine);
        Specification<Lang> specification = buildSpecification(lang);
        List<Lang> result = langRepository.findAll(specification);
        return CollectionUtils.getFirstElement(result);
    }

    @Override
    public Lang queryLangValueByPrimaryCode(String code)
    {
        Lang lang = new Lang();
        lang.setPrimaryCode(code);
        Specification<Lang> specification = buildSpecification(lang);
        List<Lang> result = langRepository.findAll(specification);
        return CollectionUtils.getFirstElement(result);
    }

    @Override
    public Lang updateLangValue(Lang lang)
    {
        return langRepository.save(lang);
    }

    @Override
    public void removeLangValue(Lang lang)
    {
        langRepository.delete(lang);
    }

    @Override
    public void batchRemoveLangValue(List<Lang> lang)
    {
        langRepository.deleteInBatch(lang);
    }

    @Override
    public List<Lang> queryLangValue()
    {
        return langRepository.findAll();
    }

    private Specification<Lang> buildSpecification(Lang condition)
    {
        return (Specification<Lang>) (root, criteriaQuery, criteriaBuilder) ->
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
