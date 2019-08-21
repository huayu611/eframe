package com.huayu.eframe.management.single.impl;

import com.huayu.eframe.global.dict.common.DictionaryUtils;
import com.huayu.eframe.management.atom.*;
import com.huayu.eframe.management.bo.*;
import com.huayu.eframe.management.cache.SecurityCacheFacade;
import com.huayu.eframe.management.common.constants.ManagementErrorCode;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.management.single.RoleService;
import com.huayu.eframe.management.single.bo.PermissionDetail;
import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.menu.bo.Menu;
import com.huayu.eframe.menu.cache.MenuMetaCache;
import com.huayu.eframe.menu.service.MenuDetail;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Administrator on 2018/7/21.
 */
@Component
public class RoleServiceImpl implements RoleService
{
    private static final LogDebug debug = new LogDebug(RoleServiceImpl.class);

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";

    @Autowired
    private RoleAtom roleAtom;

    @Autowired
    private PermissionAtom permissionAtom;

    @Autowired
    private RolePermissionAtom rolePermissionAtom;

    @Autowired
    private RoleStaffAtom roleStaffAtom;

    @Autowired
    private RoleMenuAtom roleMenuAtom;

    @Autowired
    private MenuMetaCache menuMetaCache;

    @Autowired
    private SecurityServiceImplUtil securityServiceImplUtil;

    @Override
    public RoleDetail addRole(RoleDetail roleDetail, String permissionCodes)
    {
        Role role = coverRole(roleDetail);
        Date now = LocalAttribute.getNow();
        role.setCreateTime(now);
        role.setLastUpdateTime(now);
        Role roleTemp = roleAtom.insert(role);
        if (null == roleTemp.getParentRoleId())
        {
            roleTemp.setParentRoleId(role.getId());
            roleTemp.setTopRoleId(role.getId());
            roleTemp = roleAtom.update(role);

        }


        List<RoleMenu> resultRoleMenu = processRoleMenu(roleTemp,roleDetail);
        RoleDetail roleDetailReturn = getRoleDetail(roleTemp,resultRoleMenu);
        roleDetailReturn.setPermissions(processPermission(role, permissionCodes));
        SecurityCacheFacade.refreshByLocalFlow();
        return roleDetailReturn;
    }

    @Override
    public String deleteRole(String roleCode)
    {

        Role role = getRoleByRoleCode(roleCode);
        if (null == role)
        {
            String[] exceptionParam = new String[]{roleCode};
            throw new IFPException(ManagementErrorCode.ROLE_ROLE_CODE_NOT_EXIST, "Role code not exist", exceptionParam);
        }

        Date now = DateUtils.getCurrentDate();
        role.setExpireTime(now);
        role.setLastUpdateTime(now);
        Role roleDeleteOri = roleAtom.update(role);
        deleteRolePermissionByRoleId(role.getId());
        deleteStaffRoleByRoleId(role.getId());
        SecurityCacheFacade.refreshByLocalFlow();
        return null == roleDeleteOri ? "" : roleDeleteOri.getRoleCode();
    }

    @Override
    public RoleDetail modifyRole(RoleDetail modifyRole, String permissions)
    {
        debug.log(modifyRole, permissions);
        Role role = getRoleByRoleCode(modifyRole.getCode());
        if (null == role)
        {
            String[] exceptionParam = new String[]{modifyRole.getCode()};
            throw new IFPException(ManagementErrorCode.ROLE_ROLE_CODE_NOT_EXIST, "Role code not exist", exceptionParam);
        }

        if (StringUtils.isNotNullAndEmpty(modifyRole.getName()))
        {
            role.setName(modifyRole.getName());
        }
        if (StringUtils.isNotNullAndEmpty(modifyRole.getStatus()))
        {
            role.setStatus(modifyRole.getStatus());
        }
        if (null != modifyRole.getExp())
        {
            role.setExpireTime(modifyRole.getExp());
        }
        if (StringUtils.isNotNullAndEmpty(modifyRole.getRemark()))
        {
            role.setRemark(modifyRole.getRemark());
        }
        role.setLastUpdateTime(LocalAttribute.getNow());
        Role roleReturn = roleAtom.update(role);




        List<RoleMenu> resultRoleMenu = processRoleMenu(roleReturn,modifyRole);
        RoleDetail roleDetailReturn = getRoleDetail(roleReturn,resultRoleMenu);
        if(StringUtils.isNotNullAndEmpty(modifyRole.getRoleMenus()))
        {
            List<MenuDetail> result = processMenuCodes(role,modifyRole.getRoleMenus());
            roleDetailReturn.setMenus(result);
        }
        debug.log(permissions);
        if (null == permissions)
        {
            return roleDetailReturn;
        }
        List<PermissionDetail> pds = processPermissionCodes(roleReturn, permissions);
        roleDetailReturn.setPermissions(pds);
        SecurityCacheFacade.refreshByLocalFlow();
        return roleDetailReturn;
    }

    @Override
    public RoleDetail queryRoleByCode(String roleCode)
    {
        Role role = getRoleByRoleCode(roleCode);
        if (null == role)
        {
            return null;
        }
        RoleDetail rd = queryRoleDetailByID(role.getId());
        return rd;
    }

    @Override
    public PageObject queryRoleByCondition(RoleDetail request, PagingRequest pagingRequest)
    {

        PageObject pageObject = new PageObject();
        Role condition = new Role();
        FramePaging fp = null;
        if (StringUtils.isNotNullAndEmpty(request.getCode()))
        {
            condition.setRoleCode(request.getCode());
        }
        if (StringUtils.isNotNullAndEmpty(request.getName()))
        {
            condition.setName(request.getName());
        }

        if (StringUtils.isNotNullAndEmpty(request.getStatus()))
        {
            condition.setStatus(request.getStatus());
        }
        if (null != pagingRequest)
        {
            fp = new FramePaging();
            fp.setPage(pagingRequest.getPage());
            fp.setSize(pagingRequest.getSize());
        }
        debug.log(fp);
        Date now = LocalAttribute.getNow();
        if (null != fp)
        {
            Page<Role> result = roleAtom.queryValidRoleByPage(fp, now, condition);
            PagingResponse pagingResponse = new PagingResponse();
            pagingResponse.setTotal(result.getTotalElements());
            pagingResponse.setCurrentPage(result.getNumber());
            pagingResponse.setTotalPage(result.getTotalPages());
            pageObject.setPagingResponse(pagingResponse);
            pageObject.setResponse(getRoleDetailList(result.getContent()));
        }
        else
        {
            List<Role> roleList = roleAtom.queryRoles(condition, LocalAttribute.getNow());
            pageObject.setResponse(getRoleDetailList(roleList));
        }

        return pageObject;
    }


    private RolePermission addRolePermisionById(Long roleId, Long permissionId)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        List<RolePermission> temp = rolePermissionAtom.queryRolePermission(rolePermission);
        if (CollectionUtils.isNotEmpty(temp))
        {
            return CollectionUtils.getFirstElement(temp);
        }
        RolePermission rp = rolePermissionAtom.insert(rolePermission);
        SecurityCacheFacade.refreshByLocalFlow();
        return rp;
    }

    private void deleteRolePermissionByEntity(RolePermission rolePermissio)
    {
        rolePermissionAtom.delete(rolePermissio);
        SecurityCacheFacade.refreshByLocalFlow();
    }

    private Role coverRole(RoleDetail roleDetail)
    {
        Role role = new Role();
        role.setRoleCode(roleDetail.getCode());
        role.setName(roleDetail.getName());
        role.setExpireTime(null == roleDetail.getExp() ? DateUtils.getDefaultExpireDate() : roleDetail.getExp());
        role.setEffectiveTime(null == roleDetail.getEff() ? LocalAttribute.getNow() : roleDetail.getEff());
        role.setStatus(StringUtils.isNullOrEmpty(roleDetail.getStatus()) ? SecurityConstant.STATUS.NORMAL : roleDetail.getStatus());
        Role parentRole = null;
        if (StringUtils.isNotNullAndEmpty(roleDetail.getParent()))
        {
            parentRole = getRoleByRoleCode(roleDetail.getParent());
            if (null == parentRole)
            {
                String[] exceptionParam = {roleDetail.getParent()};
                throw new IFPException(ManagementErrorCode.ADD_ROLE_PARENT_ROLE_NOT_EXIST, "Parent role not exist!", exceptionParam);
            }
        }
        if (null != parentRole)
        {
            role.setParentRoleId(parentRole.getId());
            role.setTopRoleId(parentRole.getTopRoleId());
        }
        role.setRemark(roleDetail.getRemark());
        debug.log(role);
        return role;
    }

    private RoleDetail queryRoleDetailByID(Long roleId)
    {

        Role role = getRleByRoleID(roleId);
        if (null == role)
        {
            return null;
        }
        RoleDetail roleDetail = getRoleDetail(role);
        roleDetail.setPermissions(coverPermissionDetailList(role.getId()));
        return roleDetail;
    }

    private RoleDetail getRoleDetail(Role role)
    {
        return getRoleDetail(role,null);
    }

    private RoleDetail getRoleDetail(Role role,List<RoleMenu> roleMenus)
    {
        RoleDetail roleDetail = new RoleDetail();
        roleDetail.setName(role.getName());
        roleDetail.setCode(role.getRoleCode());
        roleDetail.setEff(role.getEffectiveTime());
        roleDetail.setExp(role.getExpireTime());
        roleDetail.setStatus(role.getStatus());
        String statusName = DictionaryUtils.getDictNameDictKeyAndValue("role_status", role.getStatus());
        roleDetail.setStatusName(statusName);
        Role parentRole = getRleByRoleID(role.getParentRoleId());
        Role topRole = getRleByRoleID(role.getTopRoleId());
        if (null != parentRole)
        {
            roleDetail.setParent(parentRole.getRoleCode());
        }
        if (null != topRole)
        {
            roleDetail.setTop(topRole.getRoleCode());
        }

        roleDetail.setRemark(role.getRemark());
        roleDetail.setPermissions(coverPermissionDetailList(role.getId()));
        if(CollectionUtils.isNotEmpty(roleMenus))
        {
            List<String> currentList = new ArrayList<>();
            CollectionUtils.iterator(roleMenus,(c,v,i)->{
                currentList.add(v.getMenu().getCode());
            });
            roleDetail.setCurrentMenus(currentList);
        }
        return roleDetail;
    }

    public List<PermissionDetail> coverPermissionDetailList(Long roleID)
    {
        debug.beginLog();
        List<PermissionDetail> details = new ArrayList<>();
        RolePermission rolePermissionEntity = new RolePermission();
        rolePermissionEntity.setRoleId(roleID);
        List<RolePermission> rolePermissionList = rolePermissionAtom.queryRolePermission(rolePermissionEntity);
        if (CollectionUtils.isEmpty(rolePermissionList))
        {
            return null;
        }
        for (RolePermission rolePermission : rolePermissionList)
        {

            Permission permission = permissionAtom.getPermissionByID(rolePermission.getPermissionId());
            if (null == permission)
            {
                continue;
            }
            PermissionDetail detail = securityServiceImplUtil.getPermissionDetail(permission);
            details.add(detail);
        }
        debug.endLog();
        return details;
    }


    private List<RoleDetail> getRoleDetailList(List<Role> roleInfoList)
    {
        debug.log(roleInfoList);
        if (CollectionUtils.isEmpty(roleInfoList))
        {
            return new ArrayList<>();
        }
        List<RoleDetail> details = new ArrayList<>();
        for (Role backBo : roleInfoList)
        {
            RoleDetail detail = getRoleDetail(backBo);
            details.add(detail);
        }

        return details;
    }


    public List<PermissionDetail> processPermissionCodes(Role role, String codes)
    {
        debug.log(codes);
        List<RolePermission> rolePermissionList = getRolePermissionByRoleID(role.getId());
        Map<Long, RolePermission> permissionMap = mappingRolePermission(rolePermissionList);
        List<RolePermission> removeList = new ArrayList<>();
        List<Long> addList = new ArrayList<>();

        List<Long> updatePermission = new ArrayList<>();
        if (StringUtils.isNotNullAndEmpty(codes))
        {
            String[] codeArr = org.springframework.util.StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
            for (String permissionCode : codeArr)
            {
                debug.log(permissionCode);
                List<Permission> permissions = permissionAtom.getPermissionByCode(permissionCode, LocalAttribute.getNow());
                Permission permission = CollectionUtils.getFirstElement(permissions);
                if (null == permission)
                {
                    continue;
                }
                RolePermission rolePermission = permissionMap.get(permission.getId());
                if (null == rolePermission)
                {
                    addList.add(permission.getId());
                }
                updatePermission.add(permission.getId());
            }
        }
        if (CollectionUtils.isNotEmpty(rolePermissionList))
        {
            for (RolePermission rolePermission : rolePermissionList)
            {
                if (!updatePermission.contains(rolePermission.getPermissionId()))
                {
                    removeList.add(rolePermission);
                }
            }
        }
        addRolePermissionRelation(role, addList);
        removeRolePermissionRelation(removeList);

        RolePermission rpCondition = new RolePermission();
        rpCondition.setRoleId(role.getId());

        List<RolePermission> rolePermissions = rolePermissionAtom.queryRolePermission(rpCondition);

        if (CollectionUtils.isEmpty(rolePermissions))
        {
            return null;
        }
        List<PermissionDetail> pds = buildPermissionDetails(rolePermissions);

        return pds;
    }

    private List<PermissionDetail> buildPermissionDetails(List<RolePermission> rolePermissions)
    {
        if (CollectionUtils.isEmpty(rolePermissions))
        {
            return null;
        }
        List<PermissionDetail> result = new ArrayList<>();
        for (RolePermission rp : rolePermissions)
        {
            Permission permission = permissionAtom.getPermissionByID(rp.getPermissionId());
            PermissionDetail permissionDetail = securityServiceImplUtil.getPermissionDetail(permission);
            result.add(permissionDetail);

        }
        return result;
    }

    private List<RolePermission> getRolePermissionByRoleID(Long roleID)
    {
        RolePermission rp = new RolePermission();
        rp.setRoleId(roleID);

        List<RolePermission> rolePermissionList = rolePermissionAtom.queryRolePermission(rp);
        return rolePermissionList;
    }

    private Map<Long, RolePermission> mappingRolePermission(List<RolePermission> allRolePermissions)
    {
        Map<Long, RolePermission> result = new HashMap<>();
        if (CollectionUtils.isNotEmpty(allRolePermissions))
        {
            for (RolePermission rp : allRolePermissions)
            {
                result.put(rp.getId(), rp);
            }
        }
        return result;
    }


    private void addRolePermissionRelation(Role role, List<Long> permisionList)
    {
        debug.log(permisionList);
        if (CollectionUtils.isEmpty(permisionList))
        {
            return;
        }
        else
        {
            for (Long id : permisionList)
            {
                debug.log(id);
                addRolePermisionById(role.getId(), id);
            }
        }
    }

    private void removeRolePermissionRelation(List<RolePermission> permisionList)
    {
        debug.log(permisionList);
        if (CollectionUtils.isEmpty(permisionList))
        {
            return;
        }
        else
        {
            for (RolePermission id : permisionList)
            {
                debug.log(id);
                deleteRolePermissionByEntity(id);
            }
        }
    }

    private List<PermissionDetail> processPermission(Role role, String codes)
    {

        if (StringUtils.isNotNullAndEmpty(codes))
        {
            String[] codeArr = org.springframework.util.StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
            List<PermissionDetail> permissionDetails = new ArrayList<>();
            for (String code : codeArr)
            {
                List<Permission> permissions = permissionAtom.getPermissionByCode(code, LocalAttribute.getNow());
                Permission permission = CollectionUtils.getFirstElement(permissions);
                if (null != permission)
                {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(role.getId());
                    rolePermission.setRoleId(permission.getId());
                    RolePermission rolePermissionResult = rolePermissionAtom.insert(rolePermission);
                    PermissionDetail permissionDetail = securityServiceImplUtil.getPermissionDetail(permission);
                    permissionDetails.add(permissionDetail);
                }
            }
            return permissionDetails;
        }
        return null;
    }


    private Role getRleByRoleID(Long roleID)
    {
        return roleAtom.queryRoleByID(roleID);
    }


    private Role getRoleByRoleCode(String roleCode)
    {
        Role role = new Role();
        role.setRoleCode(roleCode);

        List<Role> roles = roleAtom.queryRoles(role, LocalAttribute.getNow());
        return CollectionUtils.getFirstElement(roles);
    }

    private void deleteRolePermissionByRoleId(Long roleId)
    {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        List<RolePermission> allPermission = rolePermissionAtom.queryRolePermission(rolePermission);
        rolePermissionAtom.delete(allPermission);
    }

    private void deleteStaffRoleByRoleId(Long roleId)
    {
        RoleStaff roleStaff = new RoleStaff();
        roleStaff.setRoleId(roleId);
        List<RoleStaff> allRoleStaff = roleStaffAtom.queryRoleStaff(roleStaff);
        roleStaffAtom.delete(allRoleStaff);
    }

    private List<RoleMenu> processRoleMenu(Role role, RoleDetail roleDetail)
    {
        List<String> addMenu = roleDetail.getAddMenus();
        List<String> removeMenu = roleDetail.getRemoveMenus();

        if(CollectionUtils.isEmpty(addMenu)&&CollectionUtils.isEmpty(removeMenu))
        {
            return roleMenuAtom.queryRoleMenuByRole(role);

        }


        List<RoleMenu> roleMenu = roleMenuAtom.queryRoleMenuByRole(role);

        //转map
        Map<String, RoleMenu> roleMenuMap = new HashMap<>();
        CollectionUtils.iterator(roleMenu, (c, v, i) ->
        {
            if (null != v && null != v.getMenu())
            {
                roleMenuMap.put(v.getMenu().getCode(), v);
            }
        });

        List<RoleMenu> addRoleMenu = new ArrayList<>();
        List<RoleMenu> removeRoleMenu = new ArrayList<>();

        CollectionUtils.iterator(addMenu,(c,v,i)->{
            String addMenuCode = v;
            if(StringUtils.isNotNullAndEmpty(addMenuCode))
            {
                if(!roleMenuMap.containsKey(addMenuCode))
                {
                    Menu menuInfo = menuMetaCache.queryMenuByCode(addMenuCode);
                    if(null ==  menuInfo)
                    {
                        throw new IFPException();
                    }
                    RoleMenu roleMenuForAddTemp = new RoleMenu();
                    roleMenuForAddTemp.setRole(role);
                    roleMenuForAddTemp.setMenu(menuInfo);
                    addRoleMenu.add(roleMenuForAddTemp);
                }

            }
        });
        CollectionUtils.iterator(removeMenu,(c,v,i)->{
            String removeMenuCode = v;
            if(StringUtils.isNotNullAndEmpty(removeMenuCode))
            {
                RoleMenu roleMenuInfo = roleMenuMap.get(removeMenuCode);
                if(null !=  roleMenuInfo)
                {
                    removeRoleMenu.add(roleMenuInfo);
                }

            }
        });
        if(CollectionUtils.isNotEmpty(addRoleMenu))
        {
            roleMenuAtom.batchAddRoleMenu(addRoleMenu);
        }
        if(CollectionUtils.isNotEmpty(removeRoleMenu))
        {
            roleMenuAtom.batchRemoveRoleMenu(removeRoleMenu);
        }
        return roleMenuAtom.queryRoleMenuByRole(role);
    }


    private List<RoleMenu> getRoleMenuByRoleId(Role role)
    {
        List<RoleMenu> roleMenuCondition = roleMenuAtom.queryRoleMenuByRole(role);
        return roleMenuCondition;
    }

    private Map<Long, RoleMenu> mappingRoleMenu(List<RoleMenu> allRoleMenu)
    {
        Map<Long, RoleMenu> result = new HashMap<>();
        if (CollectionUtils.isNotEmpty(allRoleMenu))
        {
            for (RoleMenu rm : allRoleMenu)
            {
                result.put(rm.getMenu().getMenuId(), rm);
            }
        }
        return result;
    }

    public List<MenuDetail> processMenuCodes(Role role, String codes)
    {
        debug.log(codes);
        List<RoleMenu> roleMenuList = getRoleMenuByRoleId(role);
        Map<Long, RoleMenu> roleMap = mappingRoleMenu(roleMenuList);
        List<RoleMenu> removeList = new ArrayList<>();
        List<Long> addList = new ArrayList<>();
        List<Long> updateRoleMenu = new ArrayList<>();
        if (StringUtils.isNotNullAndEmpty(codes))
        {
            String[] codeArr = org.springframework.util.StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
            for (String menuCode : codeArr)
            {
                debug.log(menuCode);
                Menu menu = menuMetaCache.queryMenuByCode(menuCode);
                if (null == menu)
                {
                    continue;
                }
                RoleMenu roleMenu  = roleMap.get(menu.getMenuId());
                if (null == roleMenu)
                {
                    addList.add(menu.getMenuId());
                }
                updateRoleMenu.add(menu.getMenuId());
            }
        }
        if (CollectionUtils.isNotEmpty(roleMenuList))
        {
            for (RoleMenu roleMenu : roleMenuList)
            {
                if (!updateRoleMenu.contains(roleMenu.getMenu().getMenuId()))
                {
                    removeList.add(roleMenu);
                }
            }
        }
        List<RoleMenu> addRoleMenuList = new ArrayList<>();
        CollectionUtils.iterator(addList,(c,v,i)->{
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRole(role);
            Menu menu = menuMetaCache.queryMenuById(v);
            roleMenu.setMenu(menu);
            addRoleMenuList.add(roleMenu);
        });
        roleMenuAtom.batchRemoveRoleMenu(removeList);
        roleMenuAtom.batchAddRoleMenu(addRoleMenuList);

        List<RoleMenu> roleMenuUpdate = roleMenuAtom.queryRoleMenuByRole(role);
        return buildMenuCodeString(roleMenuUpdate);
    }

    private List<MenuDetail> buildMenuCodeString(List<RoleMenu> roleMenus)
    {
        List<MenuDetail> menuDetails = new ArrayList<>();
        CollectionUtils.iterator(roleMenus,(c,v,i)->{
            MenuDetail menuDetail =buildMenuDetail(v.getMenu());
            menuDetails.add(menuDetail);
        });
        return menuDetails;
    }

    private MenuDetail buildMenuDetail(Menu menu)
    {
        MenuDetail menuDetail = new MenuDetail();
        menuDetail.setCode(menu.getCode());
        menuDetail.setPath(menu.getMenuPath());
        return menuDetail;
    }
}
