package com.huayu.eframe.global.email.presist.service.impl;

import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.email.presist.atom.EmailAtom;
import com.huayu.eframe.global.email.presist.bo.EmailBO;
import com.huayu.eframe.global.email.presist.cache.EmailCache;
import com.huayu.eframe.global.email.presist.service.EmailDetail;
import com.huayu.eframe.global.email.presist.service.EmailService;
import com.huayu.eframe.server.common.FramePaging;
import com.huayu.eframe.server.common.restful.PageObject;
import com.huayu.eframe.server.common.restful.PagingRequest;
import com.huayu.eframe.server.common.restful.PagingResponse;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.mvc.token.TokenUtils;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2018/9/28.
 */
@Service
public class EmailServiceImpl implements EmailService
{
    private static final LogDebug debug = new LogDebug(EmailServiceImpl.class);
    @Autowired
    private EmailAtom emailAtom;
    @Override
    public EmailDetail addEmail(EmailDetail emailDetail)
    {
        EmailBO emailBO = buildCreateEmailBo(emailDetail);
        EmailBO newEmailBo =  emailAtom.insert(emailBO);
        return buildEmailDetail(newEmailBo);
    }

    @Override
    public EmailDetail queryEmailByCode(String emailCode)
    {
        if(StringUtils.isNullOrEmpty(emailCode))
        {
            return null;
        }
        EmailBO emailBO = emailAtom.queryEmailByCode(emailCode);
        debug.log(emailBO);
        return  buildEmailDetail(emailBO);
    }

    @Override
    public String deleteEmail(String emailCode)
    {
        EmailBO emailBO = emailAtom.queryEmailByCode(emailCode);
        if(null == emailBO)
        {
            throw new IFPException(GlobalErrorCode.EMAIL_NOT_EXIST_WHEN_DELETING,"The email not exist!");
        }
        emailBO.setStatus(DELETE_STATUS);
        fixUpdateInfo(emailBO);
        EmailBO newEmailBo = emailAtom.update(emailBO);
        if(null == newEmailBo)
        {
            throw new IFPException(GlobalErrorCode.EMAIL_DELETING_FAILED,"Delete Email failed!");
        }

        return newEmailBo.getEmailCode();
    }

    @Override
    public EmailDetail updateEmail(EmailDetail emailDetail)
    {
        EmailBO emailBo = buildUpdateEmailBo(emailDetail);
        if(null == emailBo)
        {
            throw new IFPException(GlobalErrorCode.EMAIL_NOT_EXIST_WHEN_UPDATE,"The email not exist!");
        }
        EmailBO newEmailBO = emailAtom.update(emailBo);
        if(null == newEmailBO)
        {
            throw new IFPException(GlobalErrorCode.EMAIL_UPDATING_FAILED,"Update Email failed!");
        }
        return buildEmailDetail(newEmailBO);
    }

    @Override
    public PageObject queryEmailByPage(PagingRequest pagingRequest, EmailDetail emailDetail)
    {
        EmailBO emailBo = new EmailBO();
        buildEmailBO(emailDetail,emailBo);
        FramePaging framePaging = new FramePaging();
        if (null != pagingRequest)
        {
            framePaging.setPage(pagingRequest.getPage());
            framePaging.setSize(pagingRequest.getSize());
        }
        Page<EmailBO> subscriberPropertyDefinedPage =  emailAtom.queryEmailByPage(framePaging,emailBo);

        PagingResponse pagingResponse = new PagingResponse();
        pagingResponse.setTotal(subscriberPropertyDefinedPage.getTotalElements());
        pagingResponse.setCurrentPage(subscriberPropertyDefinedPage.getNumber());
        pagingResponse.setTotalPage(subscriberPropertyDefinedPage.getTotalPages());
        PageObject pageObject = new PageObject();
        pageObject.setPagingResponse(pagingResponse);
        pageObject.setResponse(buildEmailDetailList(subscriberPropertyDefinedPage.getContent()));
        return pageObject;
    }

    private List<EmailDetail> buildEmailDetailList(List<EmailBO> emailBOList)
    {
        if(CollectionUtils.isEmpty(emailBOList))
        {
            return null;
        }
        List<EmailDetail> emailDetails = new ArrayList<>();
        for(EmailBO emailItem : emailBOList)
        {
            EmailDetail emailDetail = buildEmailDetail(emailItem);
            if(null != emailDetail)
            {
                emailDetails.add(emailDetail);
            }
        }
        return emailDetails;
    }

    private EmailBO buildCreateEmailBo(EmailDetail emailDetail)
    {
        EmailBO emailBo = new EmailBO();
        emailBo.setEmailCode(emailDetail.getEmailCode());
        emailBo.setStatus(NORMAL_STATUS);
        buildEmailBO(emailDetail,emailBo);
        fixCreateInfo(emailBo);
        fixUpdateInfo(emailBo);
        return emailBo;
    }

    private EmailBO buildUpdateEmailBo(EmailDetail emailDetail)
    {
        EmailBO emailBO = emailAtom.queryEmailByCode(emailDetail.getEmailCode());
        if(null == emailBO)
        {
            return null;
        }
        buildEmailBO(emailDetail,emailBO);
        fixUpdateInfo(emailBO);
        return emailBO;
    }

    private EmailBO buildEmailBO(EmailDetail emailDetail,EmailBO emailBO)
    {

        if(null != emailDetail.getEmailPassword())
        {
            emailBO.setEmailPassword(emailDetail.getEmailPassword());
        }
        if(null != emailDetail.getHost())
        {
            emailBO.setHost(emailDetail.getHost());
        }
        if(null != emailDetail.getPort())
        {
            emailBO.setPort(emailDetail.getPort());
        }
        if(null != emailDetail.getEmailUserName())
        {
            emailBO.setEmailUserName(emailDetail.getEmailUserName());
        }
        if(null != emailDetail.getAuth())
        {
            emailBO.setAuth(emailDetail.getAuth());
        }
        if(null != emailDetail.getSender())
        {
            emailBO.setEmailSender(emailDetail.getSender());
        }
        if(null != emailDetail.getProtocol())
        {
            emailBO.setProtocol(emailDetail.getProtocol());
        }
        if(null != emailDetail.getTimeOut())
        {
            emailBO.setTimeOut(emailDetail.getTimeOut());
        }
        if(null != emailDetail.getName())
        {
            emailBO.setName(emailDetail.getName());
        }
        if(null != emailDetail.getSubject())
        {
            emailBO.setSubject(emailDetail.getSubject());
        }
        if(null != emailDetail.getPersonal())
        {
            emailBO.setPersonal(emailDetail.getPersonal());
        }
        return emailBO;
    }

    private void fixCreateInfo(EmailBO emailBO)
    {
        emailBO.setCreateTime(LocalAttribute.getNow());
        Token token = LocalAttribute.getToken();
        if (null != token)
        {
            emailBO.setCreateObjType(token.getPrimaryType());
            emailBO.setCreateObjId(getTokenPrimaryId(token));
        }
    }

    private Long getTokenPrimaryId(Token token)
    {
        TokenInstance tokenInstance = TokenUtils.getTokenInstance();
        String primaryCode = token.getPrimaryCode();
        return tokenInstance.getInstanceIdByCode(primaryCode);
    }

    private void fixUpdateInfo(EmailBO emailBO)
    {
        //staff
        TokenInstance instance = TokenUtils.getTokenInstance();
        Token token  = LocalAttribute.getToken();
        emailBO.setLastUpdateTime(LocalAttribute.getNow());
        if(null != instance && null != token)
        {
            String primaryCode = token.getPrimaryCode();
            emailBO.setLastUpdateObjId(instance.getInstanceIdByCode(primaryCode));
            emailBO.setLastUpdateObjType(instance.getType());
        }
    }

    private EmailDetail buildEmailDetail(EmailBO emailBO)
    {
        if(null == emailBO)
        {
            return null;
        }
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setEmailCode(emailBO.getEmailCode());
        emailDetail.setHost(emailBO.getHost());
        emailDetail.setProtocol(emailBO.getProtocol());
        emailDetail.setAuth(emailBO.getAuth());
        emailDetail.setSender(emailBO.getEmailSender());
        emailDetail.setPort(emailBO.getPort());
        emailDetail.setEmailUserName(emailBO.getEmailUserName());
        emailDetail.setTimeOut(emailBO.getTimeOut());
        emailDetail.setName(emailBO.getName());
        emailDetail.setPersonal(emailBO.getPersonal());
        emailDetail.setSubject(emailBO.getSubject());
        return emailDetail;
    }
}
