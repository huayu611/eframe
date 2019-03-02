package com.huayu.eframe.feedback.service.impl;

import com.huayu.eframe.feedback.atom.FeedBackAtom;
import com.huayu.eframe.feedback.bo.FeedBackBO;
import com.huayu.eframe.feedback.requset.DeleteFeedBackRequest;
import com.huayu.eframe.feedback.requset.FeedBackRequest;
import com.huayu.eframe.feedback.response.FeedBackDetail;
import com.huayu.eframe.feedback.response.FeedBackResponse;
import com.huayu.eframe.feedback.service.FeedBackService;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.DateUtils;
import com.huayu.eframe.server.tool.constants.Constants;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Leo on 2018/11/24.
 */
@Service
public class FeedBackServiceImpl implements FeedBackService
{
    private static final LogDebug debug = new LogDebug(FeedBackServiceImpl.class);


    @Autowired
    private FeedBackAtom feedBackAtom;
    @Override
    public void addFeedBack(FeedBackRequest request)
    {
        FeedBackBO feedBackBO = buildFeedBackBO(request);
        feedBackBO.setCreateTime(LocalAttribute.getNow());
        feedBackAtom.insert(feedBackBO);
    }

    @Override
    public void deleteFeedBack(DeleteFeedBackRequest request)
    {
        feedBackAtom.deleteFeedBackById(request.getId());
    }

    @Override
    public FeedBackResponse queryFeedBack()
    {
        List<FeedBackBO> allFeddBack = feedBackAtom.queryAllFeedBack();
        return getFeedBackResponse(allFeddBack);
    }



    @Override
    public PageObject queryFeedBackByPage(PagingRequest page)
    {
        FramePaging fp = new FramePaging();
        fp.setPage(page.getPage());
        fp.setSize(page.getSize());

        Page<FeedBackBO> records = feedBackAtom.queryFeedBackByPage(fp);
        return getPageObject(records);
    }

    private PageObject getPageObject(Page<FeedBackBO> records)
    {
        List<FeedBackBO> boList = records.getContent();

        FeedBackResponse response = getFeedBackResponse(boList);

        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(records.getTotalElements());
        pagingResponse.setCurrentPage(records.getNumber());
        pagingResponse.setTotalPage(records.getTotalPages());

        PageObject pageObject = new PageObject();
        pageObject.setPagingResponse(pagingResponse);
        pageObject.setResponse(response);
        debug.log("Query finish");
        debug.log(response);
        return pageObject;
    }

    @Override
    public PageObject queryFeedBackByPageAndCondition(PagingRequest page, Date start, Date end, String content)
    {
        debug.log("Enter Query :queryFeedBackByPageAndCondition ");
        FramePaging fp = new FramePaging();
        fp.setPage(page.getPage());
        fp.setSize(page.getSize());

        Page<FeedBackBO> result = feedBackAtom.queryFeedBackByPageAndDateAndContent(fp, start, end, content);
        return getPageObject(result);
    }


    public FeedBackBO buildFeedBackBO(FeedBackRequest request)
    {
        FeedBackBO feedBackBO = new FeedBackBO();
        feedBackBO.setId(request.getId());
        feedBackBO.setFdIp(request.getIp());
        feedBackBO.setFdStaffContent(request.getContent());
        feedBackBO.setFdStaffPhone(request.getMobile());
        feedBackBO.setFdStaffEmail(request.getEmail());
        feedBackBO.setFdStaffSex(request.getSex());
        feedBackBO.setFdParentId(request.getParentId());
        feedBackBO.setName(request.getName());
        return feedBackBO;
    }

    public FeedBackDetail buildFeedBackDetail(FeedBackBO bo)
    {
        debug.log(bo);
        FeedBackDetail detail = new FeedBackDetail();
        detail.setContent(bo.getFdStaffContent());
        detail.setEmail(bo.getFdStaffEmail());
        detail.setId(bo.getId());
        detail.setIp(bo.getFdIp());
        detail.setParentId(bo.getFdParentId());
        detail.setMobile(bo.getFdStaffPhone());
        detail.setSex(bo.getFdStaffSex());
        if(null != bo.getCreateTime())
        {
            detail.setCreateTime(DateUtils.dateToString(bo.getCreateTime(), Constants.DEFAULT_FORMAT_DATE_YMD));
        }
        detail.setName(bo.getName());
        return detail;
    }

    private FeedBackResponse getFeedBackResponse(List<FeedBackBO> allFeddBack)
    {
        debug.log(allFeddBack);
        if(CollectionUtils.isEmpty(allFeddBack))
        {
            return new FeedBackResponse();
        }
        List<FeedBackDetail> details = new ArrayList<>();
        for(FeedBackBO backBo: allFeddBack)
        {
            FeedBackDetail detail = buildFeedBackDetail(backBo);
            details.add(detail);
        }
        FeedBackResponse fbr = new FeedBackResponse();
        fbr.setFeedBackDetailList(details);
        debug.log(fbr);
        return fbr;
    }
}
