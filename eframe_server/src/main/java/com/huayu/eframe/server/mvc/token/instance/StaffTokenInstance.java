package com.huayu.eframe.server.mvc.token.instance;

import com.huayu.eframe.server.security.service.bo.Staff;
import com.huayu.eframe.server.security.service.cache.StaffCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2019/2/14.
 */
@Service
public class StaffTokenInstance implements TokenInstance
{
    @Autowired
    private StaffCache staffCache;


    @Override
    public String getInstanceCodeById(Long id)
    {
        Staff staff = staffCache.getStaffById(id);
        return null == staff ? null : staff.getLoginName();
    }

    @Override
    public Long getInstanceIdByCode(String code)
    {
        Staff staff = staffCache.getStaffByLoginName(code);
        return null == staff ? null : staff.getId();
    }

    @Override
    public String getType()
    {
        return "STAFF";
    }
}
