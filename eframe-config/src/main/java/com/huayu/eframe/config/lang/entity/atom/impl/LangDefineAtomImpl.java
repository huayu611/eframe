package com.huayu.eframe.config.lang.entity.atom.impl;

import com.huayu.eframe.config.lang.entity.atom.LangDefineAtom;
import com.huayu.eframe.config.lang.entity.bo.LangDefine;
import com.huayu.eframe.config.lang.entity.repository.LangDefineRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/7/22.
 */
@Service
public class LangDefineAtomImpl implements LangDefineAtom
{
    @Autowired
    private LangDefineRepository langDefineRepository;

    @Override
    public List<LangDefine> queryAllLangDefine()
    {
        return langDefineRepository.findAll();
    }

    @Override
    public List<LangDefine> addBatchLangDefine(List<LangDefine> langDefine)
    {
        return langDefineRepository.saveAll(langDefine);
    }

    @Override
    public LangDefine addLangDefine(LangDefine langDefine)
    {
        return langDefineRepository.save(langDefine);
    }

    @Override
    public LangDefine modifyLangDefine(LangDefine langDefine)
    {
        return langDefineRepository.save(langDefine);
    }

    @Override
    public LangDefine queryLangDefineByCode(String langDefineCode)
    {
        ExampleMatcher em = ExampleMatcher.matching();
        LangDefine langDefine = new LangDefine();
        langDefine.setCode(langDefineCode);
        Example<LangDefine> example = Example.of(langDefine, em);
        List<LangDefine> langDefineList = langDefineRepository.findAll(example);
        return CollectionUtils.getFirstElement(langDefineList);
    }

    @Override
    public void removeLangDefine(LangDefine langDefine)
    {
        langDefineRepository.delete(langDefine);
    }
}
