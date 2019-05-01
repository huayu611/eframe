package com.huayu.eframe.management.atom.impl;

import com.huayu.eframe.management.atom.RolePermissionAtom;
import com.huayu.eframe.management.bo.RolePermission;
import com.huayu.eframe.management.repository.RolePermissionRepository;
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
public class RolePermissionAtomImpl implements RolePermissionAtom
{
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public RolePermission insert(RolePermission rolePermission)
    {
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public RolePermission update(RolePermission rolePermission)
    {
        return rolePermissionRepository.save(rolePermission);
    }


    @Override
    public void delete(RolePermission rolePermission)
    {
        rolePermissionRepository.delete(rolePermission);
    }

    @Override
    public List<RolePermission> queryRolePermission(RolePermission rolePermission)
    {

        ExampleMatcher em = ExampleMatcher.matching();
        Example<RolePermission> example = Example.of(rolePermission, em);

        List<RolePermission> rolePermissions = rolePermissionRepository.findAll(example);
        return rolePermissions;
    }

    @Override
    public void delete(List<RolePermission> rolePermissions)
    {
        if (CollectionUtils.isEmpty(rolePermissions))
        {
            return;
        }
        for (RolePermission rp : rolePermissions)
        {
            rolePermissionRepository.delete(rp);
        }
    }
}
