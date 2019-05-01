package com.huayu.eframe.management.cache.impl;

import com.huayu.eframe.management.bo.RoleStaff;
import com.huayu.eframe.management.cache.RoleStaffCache;
import com.huayu.eframe.management.repository.RoleStaffRepository;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/18.
 */
@Service
public class RoleStaffCacheImpl extends AbstractFrameCache<RoleStaff> implements RoleStaffCache
{
    @Autowired
    private RoleStaffRepository roleStaffRepository;

    @Override
    public List<RoleStaff> load()
    {
        return roleStaffRepository.findAll();
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }

    static class RoleStaffRoleIdIndex implements Index<RoleStaff>
    {
        @Override
        public String getIndex(RoleStaff roleStaff)
        {
            return StringUtils.getString(roleStaff.getRoleId());
        }
    }

    static class RoleStaffStaffIdIndex implements Index<RoleStaff>
    {
        @Override
        public String getIndex(RoleStaff roleStaff)
        {
            return StringUtils.getString(roleStaff.getStaffId());
        }
    }


    public RoleStaffCacheImpl()
    {
        registerIndex(new RoleStaffRoleIdIndex());
        registerIndex(new RoleStaffStaffIdIndex());
    }

    @Override
    public List<RoleStaff> getAllRoleStaff()
    {
        RoleStaff roleStaff = new RoleStaff();
        List<RoleStaff> roleStaffs = getResultByIndex(DefaultIndex.class, roleStaff);
        return roleStaffs;
    }

    @Override
    public List<RoleStaff> getRoleStaffByRoleId(Long roleId)
    {
        RoleStaff roleStaff = new RoleStaff();
        roleStaff.setRoleId(roleId);
        List<RoleStaff> roleStaffs = getResultByIndex(RoleStaffRoleIdIndex.class, roleStaff);
        return roleStaffs;
    }

    @Override
    public List<RoleStaff> getRoleStaffByStaffId(Long staffId)
    {
        RoleStaff roleStaff = new RoleStaff();
        roleStaff.setStaffId(staffId);
        List<RoleStaff> roleStaffs = getResultByIndex(RoleStaffStaffIdIndex.class, roleStaff);
        return roleStaffs;
    }
}
