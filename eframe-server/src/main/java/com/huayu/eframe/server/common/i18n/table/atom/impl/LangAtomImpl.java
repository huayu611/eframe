package com.huayu.eframe.server.common.i18n.table.atom.impl;

import com.huayu.eframe.server.common.i18n.table.atom.LangAtom;
import com.huayu.eframe.server.common.i18n.table.bo.Lang;
import com.huayu.eframe.server.common.i18n.table.repository.LangRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

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
    public List<Lang> getAll()
    {
        return langRepository.findAll();
    }

    @Override
    public List<Lang> save(String langCode, List<Lang> values)
    {
        List<Lang> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(values))
        {
            for(Lang lang : values)
            {
                result.add(save(lang));
            }
        }
        return result;
    }

    @Override
    public Lang save(Lang lang)
    {
        if(null != lang)
        {
            return langRepository.save(lang);
        }
        return null;
    }

    @Override
    public List<Lang> update(String langCode, List<Lang> values)
    {
        List<Lang> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(values))
        {
            for(Lang lang : values)
            {
                if(null != lang.getId())
                {
                    result.add(langRepository.save(lang));
                }
            }
        }
        return result;
    }

    @Override
    public void delete(Lang lang)
    {
        langRepository.delete(lang);
    }

    public List<Lang> getLangsByCode(String langCode)
    {
        Lang lang = new Lang();
        lang.setLangCode(langCode);

        ExampleMatcher em = ExampleMatcher.matching();
        Example<Lang> example = Example.of(lang, em);
        return langRepository.findAll(example);
    }
}
