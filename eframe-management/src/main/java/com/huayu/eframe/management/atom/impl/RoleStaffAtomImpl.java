package com.huayu.eframe.management.atom.impl;

import com.huayu.eframe.management.atom.RoleStaffAtom;
import com.huayu.eframe.management.bo.RoleStaff;
import com.huayu.eframe.management.repository.RoleStaffRepository;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/16.
 */
@Service
public class RoleStaffAtomImpl implements RoleStaffAtom
{
    @Autowired
    private RoleStaffRepository roleStaffRepository;

    @Override
    public RoleStaff insert(RoleStaff roleStaff)
    {
        return roleStaffRepository.save(roleStaff);
    }

    @Override
    public RoleStaff update(RoleStaff roleStaff)
    {
        return roleStaffRepository.save(roleStaff);
    }


    @Override
    public void deleteRoleStaff(RoleStaff roleStaff)
    {
        roleStaffRepository.delete(roleStaff);
    }

    @Override
    public void delete(List<RoleStaff> roleStaff)
    {
        if (CollectionUtils.isEmpty(roleStaff))
        {
            return;
        }
        for (RoleStaff rs : roleStaff)
        {
            roleStaffRepository.delete(rs);
        }
    }

    @Override
    public List<RoleStaff> queryRoleStaff(RoleStaff roleStaff)
    {
        ExampleMatcher em = ExampleMatcher.matching();
        Example<RoleStaff> example = Example.of(roleStaff, em);

        List<RoleStaff> roleStaffsList = roleStaffRepository.findAll(example);
        return roleStaffsList;
    }

    @Override
    public List<RoleStaff> queryRoleStaffByBothId(Long staffId, Long roleId)
    {
        RoleStaff rs = new RoleStaff();
        rs.setStaffId(staffId);
        rs.setRoleId(roleId);
        return queryRoleStaff(rs);
    }

    @Override
    public List<RoleStaff> queryRoleStaffByStaffId(Long staffId)
    {
        RoleStaff rs = new RoleStaff();
        rs.setStaffId(staffId);
        return queryRoleStaff(rs);
    }
}
