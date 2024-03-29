package com.huayu.eframe.management.common.token;

import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.management.cache.StaffCache;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
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

    @Autowired
    private StaffService staffService;


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
    public Object getObject(String code)
    {
        return staffService.queryStaffDetail(code);
    }

    @Override
    public String getType()
    {
        return "STAFF";
    }
}
