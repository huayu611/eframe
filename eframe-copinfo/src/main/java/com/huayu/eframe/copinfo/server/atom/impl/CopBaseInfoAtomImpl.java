package com.huayu.eframe.copinfo.server.atom.impl;

import com.huayu.eframe.copinfo.server.atom.CopBaseInfoAtom;
import com.huayu.eframe.copinfo.server.bo.CopBaseInfo;
import com.huayu.eframe.copinfo.server.repository.CopBaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class CopBaseInfoAtomImpl implements CopBaseInfoAtom
{

    @Autowired
    private CopBaseInfoRepository copBaseInfoRepository;

    @Override
    public CopBaseInfo insert(CopBaseInfo copBaseInfo)
    {
        return copBaseInfoRepository.save(copBaseInfo);
    }

    @Override
    public CopBaseInfo update(CopBaseInfo copBaseInfo)
    {
        if (null == copBaseInfo.getId())
        {
            return null;
        }
        return copBaseInfoRepository.save(copBaseInfo);
    }

    @Override
    public List<CopBaseInfo> query()
    {
        return copBaseInfoRepository.findAll();
    }
}
