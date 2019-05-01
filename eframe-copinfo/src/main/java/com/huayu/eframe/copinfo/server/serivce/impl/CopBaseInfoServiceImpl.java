package com.huayu.eframe.copinfo.server.serivce.impl;

import com.huayu.eframe.copinfo.server.atom.CopBaseInfoAtom;
import com.huayu.eframe.copinfo.server.bo.CopBaseInfo;
import com.huayu.eframe.copinfo.server.serivce.CopBaseInfoService;
import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2019/1/18.
 */
@Service
public class CopBaseInfoServiceImpl implements CopBaseInfoService
{
    @Autowired
    private CopBaseInfoAtom copBaseInfoAtom;

    @Override
    public CopBaseInfoDetail addCopBaseInfoDetail(CopBaseInfoDetail detail)
    {
        CopBaseInfo copBaseInfo = buildCopBaseInfo(detail);
        CopBaseInfo result = copBaseInfoAtom.insert(copBaseInfo);
        if (null != copBaseInfo)
        {
            return buildCopBaseInfoDetail(result);
        }
        return null;
    }

    @Override
    public CopBaseInfoDetail updateCopBaseInfoDetail(CopBaseInfoDetail detail)
    {
        CopBaseInfo copDetail = queryOne();
        if (null == copDetail)
        {
            return null;
        }
        copDetail.setName(detail.getName());
        copDetail.setBrief(detail.getBrief());
        copDetail.setLogo(detail.getLogo());
        copDetail.setCityName(detail.getCityName());
        copDetail.setCityCode(detail.getCityCode());
        copDetail.setProvinceName(detail.getProvinceName());
        copDetail.setProvinceCode(detail.getProvinceCode());
        copDetail.setCountry(detail.getCountry());
        copDetail.setEmail(detail.getEmail());
        copDetail.setPhone(detail.getPhone());
        copDetail.setAddress(detail.getAddress());

        CopBaseInfo updateResult = copBaseInfoAtom.update(copDetail);
        if (null != updateResult)
        {
            return buildCopBaseInfoDetail(updateResult);
        }
        return null;
    }

    @Override
    public CopBaseInfoDetail query()
    {
        CopBaseInfo firstOne = queryOne();
        if (null != firstOne)
        {
            return buildCopBaseInfoDetail(firstOne);
        }
        return null;
    }

    private CopBaseInfo queryOne()
    {
        List<CopBaseInfo> resultList = copBaseInfoAtom.query();
        return CollectionUtils.getFirstElement(resultList);

    }


    private CopBaseInfo buildCopBaseInfo(CopBaseInfoDetail detail)
    {
        CopBaseInfo copBaseInfo = new CopBaseInfo();
        copBaseInfo.setBrief(detail.getBrief());
        copBaseInfo.setLogo(detail.getLogo());
        copBaseInfo.setName(detail.getName());
        copBaseInfo.setEmail(detail.getEmail());
        copBaseInfo.setAddress(detail.getAddress());
        copBaseInfo.setPhone(detail.getPhone());
        copBaseInfo.setCountry(detail.getCountry());
        copBaseInfo.setProvinceCode(detail.getProvinceCode());
        copBaseInfo.setProvinceName(detail.getProvinceName());
        copBaseInfo.setCityCode(detail.getCityCode());
        copBaseInfo.setCityName(detail.getCityName());
        return copBaseInfo;
    }

    private CopBaseInfoDetail buildCopBaseInfoDetail(CopBaseInfo copBaseInfo)
    {
        CopBaseInfoDetail copBaseInfoDetail = new CopBaseInfoDetail();
        copBaseInfoDetail.setBrief(copBaseInfo.getBrief());
        copBaseInfoDetail.setLogo(copBaseInfo.getLogo());
        copBaseInfoDetail.setName(copBaseInfo.getName());
        copBaseInfoDetail.setEmail(copBaseInfo.getEmail());
        copBaseInfoDetail.setAddress(copBaseInfo.getAddress());
        copBaseInfoDetail.setPhone(copBaseInfo.getPhone());
        copBaseInfoDetail.setCountry(copBaseInfo.getCountry());
        copBaseInfoDetail.setProvinceCode(copBaseInfo.getProvinceCode());
        copBaseInfoDetail.setProvinceName(copBaseInfo.getProvinceName());
        copBaseInfoDetail.setCityCode(copBaseInfo.getCityCode());
        copBaseInfoDetail.setCityName(copBaseInfo.getCityName());
        return copBaseInfoDetail;
    }
}
