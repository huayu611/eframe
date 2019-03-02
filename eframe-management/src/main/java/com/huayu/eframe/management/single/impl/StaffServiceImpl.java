package com.huayu.eframe.management.single.impl;

import com.huayu.eframe.management.atom.RoleAtom;
import com.huayu.eframe.management.atom.RoleStaffAtom;
import com.huayu.eframe.management.atom.StaffAtom;
import com.huayu.eframe.management.bo.Role;
import com.huayu.eframe.management.bo.RoleStaff;
import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.management.cache.SecurityCacheFacade;
import com.huayu.eframe.management.constant.SecurityConstant;
import com.huayu.eframe.management.single.StaffService;
import com.huayu.eframe.management.single.bo.RoleDetail;
import com.huayu.eframe.management.single.bo.StaffDetail;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.ErrorCode;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.basic.RandomUtils;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.encrypt.Encrypt;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/7/21.
 */
@Component
public class StaffServiceImpl implements StaffService
{
    private static final LogDebug debug = new LogDebug(StaffServiceImpl.class);

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";

    @Autowired
    private StaffAtom staffAtom;

    @Autowired
    private RoleAtom roleAtom;

    @Autowired
    private RoleStaffAtom roleStaffAtom;

    @Autowired
    private SecurityServiceImplUtil securityServiceImplUtil;


    @Override
    public StaffDetail checkLogin(String loginName, String password)
    {
        debug.beginLog();

        Staff loginStaffObject = new Staff();
        loginStaffObject.setLoginName(loginName);
        List<Staff> staffList = staffAtom.queryStaff(loginStaffObject);

        if(CollectionUtils.isEmpty(staffList))
        {
            debug.log("Staffname or password wrong");
            throw new IFPException(ErrorCode.STAFFNAME_OR_PASSWORD_WRONG,"Staffname or password wrong!");
        }
        Staff staff = getCurrentValidStaff(staffList);

        if(null == staff)
        {
            debug.log("Staffname or password wrong");
            throw new IFPException(ErrorCode.LOGIN_NAME_STATUS_ERROR,"Staff status incorrect");
        }

        String passwordEncry = Encrypt.getMD5Code(password + staff.getSalt()+loginName);

        if(StringUtils.equalString(staff.getPassword(),passwordEncry))
        {

            updateStaffLastLoginTime(staff, LocalAttribute.getNow());
            debug.endLog();
            return getStaffDetail(staff);
        }
        debug.log("Staffname or password wrong");
        throw new IFPException(ErrorCode.STAFFNAME_OR_PASSWORD_WRONG,"Staffname or password wrong!");
    }

    private Staff queryStaffByLoginName(String staffname)
    {
        List<Staff> staffList = staffAtom.queryStaffByLogin(staffname,LocalAttribute.getNow());
        if(CollectionUtils.isEmpty(staffList))
        {
            return null;
        }
        Staff staff = CollectionUtils.getFirstElement(staffList);
        return staff;
    }

    @Override
    public void updateStaffLastLoginTime(Staff staff, Date date)
    {
        staff.setLastLoginTime(date);
        staffAtom.update(staff);
        SecurityCacheFacade.refreshByLocalFlow();
    }

    private Staff getCurrentValidStaff(List<Staff> staffList)
    {
        Staff result = null;
        for(Staff staff : staffList)
        {
            if(!DateUtils.isBetweenDate(staff.getEffectiveTime(),LocalAttribute.getNow(), staff.getExpireTime()))
            {
                continue;
            }
            if(!SecurityConstant.STATUS.NORMAL.equals(staff.getStatus()))
            {
                continue;
            }
            result = staff;
            break;

        }
        return result;
    }

    @Override
    public StaffDetail addStaff(StaffDetail staffDetail, String password, String roles)
    {
        debug.beginLog();

        Staff staff = buildStaffByDetail(staffDetail);
        staff.setPassword(password);

        String salt = RandomUtils.getRandomCharString(16);
        String passwordEncry = Encrypt.getMD5Code(staff.getPassword() + salt + staff.getLoginName());
        Date now = LocalAttribute.getNow();

        staff.setLastLoginTime(now);
        staff.setCreateTime(now);
        staff.setSalt(salt);
        staff.setPassword(passwordEncry);
        staff.setLastUpdateTime(now);

        Staff staffReturn = staffAtom.insert(staff);


        StaffDetail returnDetail = getStaffDetail(staffReturn);

        List<RoleDetail> roleDetails = addStaffRole(staffReturn, roles);
        if(CollectionUtils.isNotEmpty(roleDetails))
        {
            returnDetail.setRoles(roleDetails);
        }
        debug.endLog();
        SecurityCacheFacade.refreshByLocalFlow();
        return returnDetail;

    }


    @Override
    public String deleteStaff(String loginName)
    {
        Staff staff = queryStaffByLoginName(loginName);
        if(null == staff)
        {
            return "";
        }
        staff.setExpireTime(LocalAttribute.getNow());
        staff.setLastUpdateTime(LocalAttribute.getNow());
        staff.setStatus("D");
        Staff expiredStaff = staffAtom.update(staff);
        deleteRoleStaffByStaff(staff.getId());
        SecurityCacheFacade.refreshByLocalFlow();
        return expiredStaff.getLoginName();
    }

    private Staff checkStaffExistOrNot(String loginName)
    {
        Staff staff = queryStaffByLoginName(loginName);
        debug.log(staff);
        if (null == staff)
        {
            String[] exceptionParam = new String[]{loginName};
            throw new IFPException(ErrorCode.STAFF_LOGIN_NAME_NOT_EXIST, "Login name not exist!", exceptionParam);
        }
        return staff;
    }

    @Override
    public StaffDetail updateStaff(StaffDetail staffDetail,String roles)
    {
        debug.log(staffDetail);
        Staff existStaff = checkStaffExistOrNot(staffDetail.getLogin());
        Staff staff = coverRequestToStaff(existStaff,staffDetail);
        staff.setId(existStaff.getId());
        staff.setLastUpdateTime(DateUtils.getCurrentDate());
        Staff resultStaff = staffAtom.update(staff);
        StaffDetail resultDetail =  getStaffDetail(resultStaff);
        SecurityCacheFacade.refreshByLocalFlow();
        if(null == roles)
        {
            return resultDetail;
        }
        List<RoleDetail> roleDetails = processModifyRoles(staff,roles);
        resultDetail.setRoles(roleDetails);

        return resultDetail;
    }

    private List<RoleDetail> processModifyRoles(Staff staff,String roles)
    {
        saveRoleList(staff,roles);
        List<RoleDetail> roleDetails = new ArrayList<>();
        List<RoleStaff> roleStaffs = roleStaffAtom.queryRoleStaffByStaffId(staff.getId());
        if(CollectionUtils.isEmpty(roleStaffs))
        {
            return null;
        }
        for(RoleStaff rs : roleStaffs)
        {
            Role role = roleAtom.queryRoleByID(rs.getRoleId());
            RoleDetail roleDetail = securityServiceImplUtil.getRoleDetail(role);
            roleDetails.add(roleDetail);
        }
        return roleDetails;
    }

    @Override
    public boolean checkExistStaff(String loginName)
    {
        Staff staff = queryStaffByLoginName(loginName);
        return null != staff;
    }

    private Staff buildStaffByDetail(StaffDetail staffDetail)
    {
        debug.beginLog();
        Staff staff = new Staff();
        staff.setLoginName(staffDetail.getLogin());
        if(null != staffDetail.getEff())
        {
            staff.setEffectiveTime(staffDetail.getEff());
        }
        if(null != staffDetail.getExp())
        {
            staff.setExpireTime(staffDetail.getExp());
        }
        staff.setStaffname(staffDetail.getName());
        staff.setStatus(staffDetail.getStatus());
        staff.setLang(staffDetail.getLang());
        staff.setEmail(staffDetail.getEmail());
        staff.setTelNumber(staffDetail.getTel());
        staff.setRemark(staffDetail.getRemark());
        debug.log(staff);
        debug.endLog();
        return staff;
    }

    private Staff coverRequestToStaff(Staff staff,StaffDetail staffDetail)
    {
        debug.beginLog();
        if(null != staffDetail.getEff())
        {
            staff.setEffectiveTime(staffDetail.getEff());
        }
        if(null != staffDetail.getExp())
        {
            staff.setExpireTime(staffDetail.getExp());
        }
        if(null != staffDetail.getName())
        {
            staff.setStaffname(staffDetail.getName());
        }
        if(null != staffDetail.getStatus())
        {
            staff.setStatus(staffDetail.getStatus());
        }
        if(null != staffDetail.getLang())
        {
            staff.setLang(staffDetail.getLang());
        }
        if(null != staffDetail.getEmail())
        {
            staff.setEmail(staffDetail.getEmail());
        }
        if(null != staffDetail.getTel())
        {
            staff.setTelNumber(staffDetail.getTel());
        }
        if(null != staffDetail.getRemark())
        {
            staff.setRemark(staffDetail.getRemark());
        }

        debug.log(staff);
        debug.endLog();
        return staff;
    }
    @Override
    public StaffDetail queryStaffDetail(String loginCode)
    {
        Staff staff =  queryStaffByLoginName(loginCode);
        if(null == staff)
        {
            return null;
        }
        StaffDetail detail = getStaffDetail(staff);
        return detail;
    }

    private StaffDetail getStaffDetail(Staff staff)
    {
        StaffDetail detail = new StaffDetail();
        detail.setName(staff.getStaffname());
        detail.setLogin(staff.getLoginName());
        detail.setLang(staff.getLang());
        detail.setEff(staff.getEffectiveTime());
        detail.setExp(staff.getExpireTime());
        detail.setStatus(staff.getStatus());
        detail.setEmail(staff.getEmail());
        detail.setRoles(buildRoleDetailList(staff.getId()));
        detail.setTel(staff.getTelNumber());
        detail.setRemark(staff.getRemark());
        return detail;
    }

    private List<StaffDetail> buildStaffDetail(List<Staff> staffList)
    {
        List<StaffDetail> resultStaffDetail = new ArrayList<>();
        if(CollectionUtils.isEmpty(staffList))
        {
            return resultStaffDetail;
        }
        for(Staff staff : staffList)
        {
            if(null == staff)
            {
                continue;
            }
            StaffDetail detail = new StaffDetail();
            detail.setName(staff.getStaffname());
            detail.setLogin(staff.getLoginName());
            detail.setLang(staff.getLang());
            detail.setEff(staff.getEffectiveTime());
            detail.setExp(staff.getExpireTime());
            detail.setStatus(staff.getStatus());
            detail.setEmail(staff.getEmail());
            detail.setTel(staff.getTelNumber());
            detail.setRoles(buildRoleDetailList(staff.getId()));
            resultStaffDetail.add(detail);
        }
        return resultStaffDetail;
    }

    @Override
    public PageObject queryStaffListByPage(StaffDetail queryStaffRequest, PagingRequest pagingRequest)
    {
        Date now = LocalAttribute.getNow();
        FramePaging framePaging = null;
        Staff staff = null;
        if(null != pagingRequest)
        {
            framePaging = new FramePaging();
            framePaging.setSize(pagingRequest.getSize());
            framePaging.setPage(pagingRequest.getPage());
        }
        if(null != queryStaffRequest)
        {
            staff = new Staff();
            staff.setLoginName(queryStaffRequest.getLogin());
            staff.setStaffname(queryStaffRequest.getName());
        }
        Page<Staff> resultList = staffAtom.queryValidStaffByPage(framePaging, now, staff);
        PageObject pageObject = new PageObject();
        pageObject.setResponse(buildStaffDetail(resultList.getContent()));

        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(resultList.getTotalElements());
        pagingResponse.setCurrentPage(resultList.getNumber());
        pagingResponse.setTotalPage(resultList.getTotalPages());
        pageObject.setPagingResponse(pagingResponse);
        return pageObject;
    }

    @Override
    public void changePassword(String loginCode, String password)
    {
        Staff staff = queryStaffByLoginName(loginCode);
        if(null == staff)
        {
            return;
        }
        String passwordEncry = Encrypt.getMD5Code(password + staff.getSalt()+ loginCode);
        staff.setPassword(passwordEncry);
        staffAtom.update(staff);

    }

    public List<RoleDetail> addStaffRole( Staff staff,String codes)
    {
        if(StringUtils.isNullOrEmpty(codes))
        {
            return null;
        }
        String[] codeArr = org.springframework.util.StringUtils.tokenizeToStringArray(codes, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        List<RoleDetail> roleDetails = new ArrayList<>();
        for(String code : codeArr)
        {
            List<Role> roles = roleAtom.queryRolesByCode(code,LocalAttribute.getNow());
            Role role = CollectionUtils.getFirstElement(roles);
            if(null == role)
            {
                continue;
            }

            RoleStaff rs = new RoleStaff();
            rs.setRoleId(role.getId());
            rs.setStaffId(staff.getId());
            RoleDetail roleDetail = securityServiceImplUtil.getRoleDetail(role);
            roleDetails.add(roleDetail);

        }
        return roleDetails;
    }

    private List<RoleDetail> buildRoleDetailList(Long id)
    {
        List<RoleStaff> ruList = roleStaffAtom.queryRoleStaffByStaffId(id);
        List<RoleDetail> roleDetailList = buildRoleDetailList(ruList);
        return roleDetailList;
    }

    private List<RoleDetail> buildRoleDetailList(List<RoleStaff> ruList )
    {
        List<RoleDetail> roleDetailList = new ArrayList<>();
        if(CollectionUtils.isEmpty(ruList))
        {
            return null;
        }
        for(RoleStaff ru : ruList)
        {
            Role role = roleAtom.queryRoleByID(ru.getRoleId());

            if(null == role)
            {
                continue;
            }
            RoleDetail roleDetail = securityServiceImplUtil.getRoleDetail(role);
            roleDetailList.add(roleDetail);
        }
        return roleDetailList;
    }

    private void saveRoleList(Staff staff,String roleString)
    {

        String[] codeArr = org.springframework.util.StringUtils.tokenizeToStringArray(roleString, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
        List<Role> roleIdListRequest = getRoleIdList(codeArr);

        List<RoleStaff> roleListExist = roleStaffAtom.queryRoleStaffByStaffId(staff.getId());
        List<Long> addRoleStaffRelationList = new ArrayList<>();
        List<RoleStaff> removeRoleStaffRelationList = new ArrayList<>();
        List<Long> roleListExistRoleId  = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(roleListExist))
        {
            for(RoleStaff roleStaff : roleListExist)
            {
                Role role = roleAtom.queryRoleByID(roleStaff.getRoleId());
                if(null == role)
                {
                    continue;
                }
                roleListExistRoleId.add(role.getId());
                if(roleIdListRequest.contains(role.getId()))
                {
                    continue;
                }
                else
                {
                    removeRoleStaffRelationList.add(roleStaff);
                }
            }
        }
        if(CollectionUtils.isNotEmpty(roleIdListRequest))
        {
            for(Role roleNeedAdd : roleIdListRequest)
            {
                if(!roleListExistRoleId.contains(roleNeedAdd.getId()))
                {
                    addRoleStaffRelationList.add(roleNeedAdd.getId());
                }
            }
        }
        debug.log(addRoleStaffRelationList);
        debug.log(removeRoleStaffRelationList);
        addStaffRoleRelation(staff,addRoleStaffRelationList);
        removeRolePermissionRelation(staff,removeRoleStaffRelationList);
    }

    private List<Role> getRoleIdList( String[] codeArr)
    {
        List<Role> roleIdList = new ArrayList<>();
        if(codeArr.length==0)
        {
            return roleIdList;
        }
        for(String code : codeArr)
        {

            List<Role> roles = roleAtom.queryRolesByCode(code, LocalAttribute.getNow());
            Role role = CollectionUtils.getFirstElement(roles);
            if(null == role)
            {
                continue;
            }
            roleIdList.add(role);
        }
        return roleIdList;
    }



    private void addStaffRoleRelation(Staff staff, List<Long> roleList)
    {
        if(CollectionUtils.isEmpty(roleList))
        {
            return;
        }
        else
        {
            for(Long id : roleList)
            {
                RoleStaff roleStaff = new RoleStaff();
                roleStaff.setStaffId(staff.getId());
                roleStaff.setRoleId(id);
                roleStaffAtom.insert(roleStaff);

            }
        }
    }

    private void removeRolePermissionRelation(Staff staff, List<RoleStaff> roleList)
    {
        if(CollectionUtils.isEmpty(roleList))
        {
            return;
        }
        else
        {
            for(RoleStaff ru : roleList)
            {
                debug.log(ru);
                if(null == ru)
                {
                    continue;
                }
                roleStaffAtom.deleteRoleStaff(ru);
            }
        }
    }

    private void deleteRoleStaffByStaff(Long staffId)
    {
        RoleStaff roleStaff = new RoleStaff();
        roleStaff.setStaffId(staffId);
        List<RoleStaff> allRoleStaff = roleStaffAtom.queryRoleStaff(roleStaff);
        roleStaffAtom.delete(allRoleStaff);
    }
}
