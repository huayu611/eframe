package com.huayu.eframe.management.cache.impl;

import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.management.cache.StaffCache;
import com.huayu.eframe.management.repository.StaffRepository;
import com.huayu.eframe.server.cache.frame.AbstractFrameCache;
import com.huayu.eframe.server.cache.frame.DefaultIndex;
import com.huayu.eframe.server.cache.frame.Index;
import com.huayu.eframe.server.common.standard.StandardFacade;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/18.
 */
@Service
public class StaffCacheImpl extends AbstractFrameCache<Staff> implements StaffCache
{

    @Autowired
    private StaffRepository staffRepository;

    static class StaffLoginNameIndex implements Index<Staff>
    {
        @Override
        public String getIndex(Staff staff)
        {
            return staff.getLoginName();
        }
    }

    static class StaffIdIndex implements Index<Staff>
    {
        @Override
        public String getIndex(Staff staff)
        {
            return StringUtils.getString(staff.getId());
        }
    }

    public StaffCacheImpl()
    {
        registerIndex(new StaffLoginNameIndex());
        registerIndex(new StaffIdIndex());
    }

    @Override
    public Staff getStaffByLoginName(String loginName)
    {
        Staff staff = new Staff();
        staff.setLoginName(loginName);
        List<Staff> staffList = getResultByIndex(StaffLoginNameIndex.class, staff);
        List<Staff> resultValid = StandardFacade.getValidItem(staffList);
        return CollectionUtils.getFirstElement(resultValid);
    }

    @Override
    public Staff getStaffById(Long id)
    {
        Staff staff = new Staff();
        staff.setId(id);
        List<Staff> staffList = getResultByIndex(StaffIdIndex.class, staff);
        return CollectionUtils.getFirstElement(staffList);
    }

    @Override
    public List<Staff> getAllStaff()
    {
        Staff staff = new Staff();
        List<Staff> staffList = getResultByIndex(DefaultIndex.class, staff);
        return staffList;
    }

    @Override
    public List<Staff> load()
    {
        return staffRepository.findAll();
    }

    @Override
    public String cacheName()
    {
        return CACHE_NAME;
    }
}
