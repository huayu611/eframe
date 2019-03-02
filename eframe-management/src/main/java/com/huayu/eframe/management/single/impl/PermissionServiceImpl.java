package com.huayu.eframe.management.single.impl;

import com.huayu.eframe.management.atom.PermissionAtom;
import com.huayu.eframe.management.atom.RolePermissionAtom;
import com.huayu.eframe.management.bo.Permission;
import com.huayu.eframe.management.bo.RolePermission;
import com.huayu.eframe.management.cache.SecurityCacheFacade;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.management.single.PermissionService;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/28.
 */
@Component
public class PermissionServiceImpl implements PermissionService
{
    private static final LogDebug debug = new LogDebug(PermissionServiceImpl.class);

    @Autowired
    private PermissionAtom permissionAtom;

    @Autowired
    private RolePermissionAtom rolePermissionAtom;

    @Autowired
    private SecurityServiceImplUtil securityServiceImplUtil;

    @Override
    @Deprecated
    public Permission getPermissionByCode(String code)
    {
        Permission rp = new Permission();
        rp.setPermissionCode(code);
        List<Permission> urlList = permissionAtom.queryPermission(rp,LocalAttribute.getNow());
        return CollectionUtils.getFirstElement(urlList);
    }

    @Override
    public PermissionDetail addPermission(PermissionDetail addPermissionDetail)
    {
        checkPermissionExist(addPermissionDetail.getCode());
        Permission permission = coverPermission(addPermissionDetail);
        Permission permissionReturn = permissionAtom.insert(permission);
        SecurityCacheFacade.refreshByLocalFlow();
        return securityServiceImplUtil.getPermissionDetail(permissionReturn);
    }

    @Override
    public void deletePermissionByCode(String code)
    {
        Permission permission = getPermissionByCode(code);
        throwPermissionNotExistException(permission, code);
        permission.setExpireTime(LocalAttribute.getNow());
        permission.setLastUpdateTime(LocalAttribute.getNow());
        permission.setStatus(SecurityConstant.STATUS.DELETE);
        permissionAtom.update(permission);


        deleteRolePermissionByPermission(permission.getId());
        SecurityCacheFacade.refreshByLocalFlow();
    }

    @Override
    public PermissionDetail updatePermission(PermissionDetail permissionDetail)
    {
        Permission permission = getPermissionByCode(permissionDetail.getCode());
        throwPermissionNotExistException(permission, permissionDetail.getCode());
        if (StringUtils.isNotNullAndEmpty(permissionDetail.getUrl()))
        {
            permission.setUrl(permissionDetail.getUrl());
        }
        if (StringUtils.isNotNullAndEmpty(permissionDetail.getWhite()))
        {
            permission.setWhite(permissionDetail.getWhite());
        }
        if (StringUtils.isNotNullAndEmpty(permissionDetail.getMethod()))
        {
            permission.setMethod(permissionDetail.getMethod());
        }
        if (StringUtils.isNotNullAndEmpty(permissionDetail.getName()))
        {
            permission.setPermissionName(permissionDetail.getName());
        }
        if (null != permissionDetail.getExp())
        {
            permission.setExpireTime(permissionDetail.getExp());
        }
        if (StringUtils.isNotNullAndEmpty(permissionDetail.getStatus()))
        {
            permission.setStatus(permissionDetail.getStatus());
        }
        permission.setLastUpdateTime(LocalAttribute.getNow());
        Permission permissionReturn = permissionAtom.update(permission);
        PermissionDetail detail = securityServiceImplUtil.getPermissionDetail(permissionReturn);
        SecurityCacheFacade.refreshByLocalFlow();
        return detail;
    }

    @Override
    public PermissionDetail queryPermissionByCode(String roleCode)
    {
        Permission permission = getPermissionByCode(roleCode);
        if(null == permission)
        {
            return null;
        }
        PermissionDetail rd = securityServiceImplUtil.getPermissionDetail(permission);
        return rd;
    }

    @Override
    public PageObject queryPermissionByPage(PermissionDetail permissionDetail,PagingRequest pagingRequest)
    {

        Date now = LocalAttribute.getNow();
        FramePaging framePaging = null;
        Permission permission = null;

        if(null != permissionDetail)
        {
            permission = new Permission();
            permission.setPermissionCode(permissionDetail.getCode());
            permission.setPermissionName(permissionDetail.getName());
        }
        if(null != pagingRequest)
        {
            framePaging = new FramePaging();
            framePaging.setSize(pagingRequest.getSize());
            framePaging.setPage(pagingRequest.getPage());
            debug.log(framePaging);
        }
        Page<Permission> resultList = permissionAtom.queryValidPermissionByPage(framePaging, now, permission);
        PageObject pageObject = new PageObject();
        List<Permission> permissionList = resultList.getContent();
        List<PermissionDetail> detailList = new ArrayList<>();
        pageObject.setResponse(detailList);
        if(CollectionUtils.isNotEmpty(permissionList))
        {
            for(Permission per : permissionList)
            {
                detailList.add(securityServiceImplUtil.getPermissionDetail(per));
            }
        }
        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(resultList.getTotalElements());
        pagingResponse.setCurrentPage(resultList.getNumber());
        pagingResponse.setTotalPage(resultList.getTotalPages());
        pageObject.setPagingResponse(pagingResponse);
        return pageObject;

    }

    @Override
    public List<PermissionDetail> queryAllPermission()
    {
        List<Permission> permissions = permissionAtom.getAll();
        List<PermissionDetail> permissionDetailList = getPermissionDetails(permissions);
        return permissionDetailList;
    }

    private PermissionDetail buildPermissionDetail(Long permissionId)
    {

        Permission permission =getPermissionByID(permissionId);
        if(null == permission)
        {
            return null;
        }
        PermissionDetail permissionDetail = securityServiceImplUtil.getPermissionDetail(permission);
        return permissionDetail;
    }


    private List<PermissionDetail> getPermissionDetails(List<Permission> resultList)
    {
        if(CollectionUtils.isEmpty(resultList))
        {
            return null;
        }
        List<PermissionDetail> result = new ArrayList<>();
        for(Permission p : resultList)
        {
            PermissionDetail detail = securityServiceImplUtil.getPermissionDetail(p);
            if(null != detail)
            {
                result.add(detail);
            }
        }
        return result;
    }




    private void checkPermissionExist(String permissionCode)
    {
        Permission permission = getPermissionByCode(permissionCode);
        if (null != permission) {
            String[] paramArr = new String[]{permissionCode};
            throw new IFPException(ErrorCode.ADD_PERMISSION_CODE_EXIST_ALREADY, "Permission exist already!", paramArr);
        }

    }

    private Permission coverPermission(PermissionDetail detail)
    {
        Permission permission = new Permission();
        permission.setUrl(detail.getUrl());
        permission.setMethod(StringUtils.isNullOrEmpty(detail.getMethod()) ? "POST" : detail.getMethod());
        permission.setCreateTime(LocalAttribute.getNow());
        permission.setExpireTime(null == detail.getExp() ? DateUtils.getDefaultExpireDate() : detail.getExp());
        permission.setEffectiveTime(null == detail.getEff() ? LocalAttribute.getNow() : detail.getEff());
        permission.setLastUpdateTime(LocalAttribute.getNow());
        permission.setPermissionType(StringUtils.isNullOrEmpty(detail.getType()) ? "1" : detail.getType());
        permission.setStatus(StringUtils.isNullOrEmpty(detail.getStatus()) ? SecurityConstant.STATUS.NORMAL : detail.getStatus());
        permission.setWhite(StringUtils.isNullOrEmpty(detail.getWhite()) ? SecurityConstant.PERMISSION_WHITE.NO : detail.getWhite());
        permission.setPermissionCode(detail.getCode());
        permission.setPermissionName(StringUtils.isNullOrEmpty(detail.getName()) ? detail.getName() : detail.getName());
        return permission;
    }


    private void throwPermissionNotExistException(Permission permission, String permissionCode)
    {
        if (null == permission)
        {
            String[] exceptionParam = new String[]{permissionCode};
            throw new IFPException(ErrorCode.P0ERMISSION_CODE_EXIST, "Role code not exist", exceptionParam);
        }
    }

    private Permission getPermissionByID(Long id)
    {
        return permissionAtom.getPermissionByID(id);
    }

    private void deleteRolePermissionByPermission(Long id)
    {
        RolePermission rp = new RolePermission();
        rp.setPermissionId(id);
        List<RolePermission> allRolePermission = rolePermissionAtom.queryRolePermission(rp);
        rolePermissionAtom.delete(allRolePermission);
    }
}
