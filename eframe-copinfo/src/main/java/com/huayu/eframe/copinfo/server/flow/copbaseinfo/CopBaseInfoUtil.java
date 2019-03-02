package com.huayu.eframe.copinfo.server.flow.copbaseinfo;

import com.huayu.eframe.copinfo.server.serivce.bo.CopBaseInfoDetail;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/1/18.
 */

public class CopBaseInfoUtil
{
    public static CopBaseInfoDetail buildCopBaseInfoDetail(CopBaseInfoMessage message )
    {
        CopBaseInfoDetail copBaseInfoDetail = new CopBaseInfoDetail();
        copBaseInfoDetail.setName(message.getName());
        copBaseInfoDetail.setBrief(message.getBrief());
        copBaseInfoDetail.setLogo(message.getLogo());
        copBaseInfoDetail.setEmail(message.getEmail());
        copBaseInfoDetail.setAddress(message.getAddress());
        copBaseInfoDetail.setCityCode(message.getCityCode());
        copBaseInfoDetail.setCityName(message.getCityName());
        copBaseInfoDetail.setProvinceCode(message.getProvinceCode());
        copBaseInfoDetail.setProvinceName(message.getProvinceName());
        copBaseInfoDetail.setPhone(message.getPhone());
        copBaseInfoDetail.setCountry(message.getCountry());
        return copBaseInfoDetail;
    }
}
