package com.huayu.eframe.server.common.found;

import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.mvc.token.Token;
import com.huayu.eframe.server.mvc.token.instance.TokenInstance;
import org.springframework.stereotype.Service;

/**
 * 获取当前操作实体,返回currentEntity,
 *
 * @See CurrentEntity
 * Created by Leo on 2019/4/1.
 */
@Service
public class CurrentObject
{
    private final static String KEY = "com.huayu.eframe.server.common.found.CurrentObject#getCurrentEntity";

    public CurrentEntity getCurrentEntity()
    {
        CurrentEntity result = LocalAttribute.getValue(KEY);
        if (null == result)
        {
            CurrentEntity ce = getCurrentEntityByAtom();
            LocalAttribute.addValue(KEY, ce);
            return ce;
        }
        return result;

    }

    private CurrentEntity getCurrentEntityByAtom()
    {
        Token token = LocalAttribute.getToken();
        if (null == token)
        {
            return new CurrentEntity();
        }
        TokenInstance tokenInstance = token.getTokenInstance();
        if (null == tokenInstance)
        {
            return new CurrentEntity();
        }
        String code = token.getPrimaryCode();
        CurrentEntity currentEntity = new CurrentEntity();
        Long id = tokenInstance.getInstanceIdByCode(code);
        currentEntity.setCode(code);
        currentEntity.setId(id);
        currentEntity.setType(token.getPrimaryType());
        currentEntity.setNow(LocalAttribute.getNow());
        return currentEntity;
    }
}
