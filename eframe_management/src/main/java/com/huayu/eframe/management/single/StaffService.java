package com.huayu.eframe.management.single;

import com.huayu.eframe.management.bo.Staff;
import com.huayu.eframe.management.single.bo.StaffDetail;
import com.huayu.eframe.server.flow.restful.PageObject;
import com.huayu.eframe.server.flow.restful.PagingRequest;

import java.util.Date;

/**
 * Created by Administrator on 2018/7/13.
 */
public interface StaffService
{
    StaffDetail checkLogin(String staffname, String password);

    void updateStaffLastLoginTime(Staff staff, Date date);

    StaffDetail addStaff(StaffDetail staffDetail, String password, String roles);

    String deleteStaff(String loginName);

    StaffDetail updateStaff(StaffDetail staffDetail, String roles);

    /**
     * 判断用户是否存在
     * true:在当前或未来生效的用户中已经存在
     * false:在当前或未来生效的用户中不存在
     * @param loginName
     * @return
     */
    boolean checkExistStaff(String loginName);


    StaffDetail queryStaffDetail(String loginCode);

    /**
     * 当前只支持Staffname,LoginName分页查询。
     * 后续增加能力，需要修改注释
     * @param queryStaffDetail
     * @param pagingRequest
     * @return
     */
    PageObject queryStaffListByPage(StaffDetail queryStaffDetail, PagingRequest pagingRequest);

    void changePassword(String loginCode, String password);

}
