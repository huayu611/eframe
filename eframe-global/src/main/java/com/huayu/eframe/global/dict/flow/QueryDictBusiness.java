package com.huayu.eframe.global.dict.flow;

import com.huayu.eframe.server.common.CommonHelper;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.global.dict.reader.DictDetail;
import com.huayu.eframe.global.dict.reader.DictLangService;
import com.huayu.eframe.global.dict.reader.DictionaryService;
import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Leo on 2019/1/7.
 */
@Service
public class QueryDictBusiness extends AbstractExecuteBusiness
{
    private static final LogDebug debug = new LogDebug(QueryDictBusiness.class);

    private final static String RESULT = "QueryDictBusiness_RESULT";

    @Autowired
    private DictionaryService dictionaryService;
    @Override
    public void execute(BusinessParameter param)
    {
        String code = param.getRequest();
        List<DictDetail> result = dictionaryService.getDict(code);

        DictResponse dictResponse = new DictResponse();
        List<Dict> dicts = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(result))
        {
            for (DictDetail dictDetail : result)
            {
                Dict dict = new Dict();
                dict.setKey(dictDetail.getCode());

                String value = buildDictName(dictDetail);
                dict.setName(value);
                dicts.add(dict);
            }
        }
        dictResponse.setDicts(dicts);
        param.addParameter(RESULT,dictResponse);
    }

    private String buildDictName(DictDetail dic)
    {
        String lang = dic.getLangCode();
        String name = dic.getName();
        if(StringUtils.isNotNullAndEmpty(name))
        {
            return name;
        }
        Locale l = null;
        if(null == LocalAttribute.getToken() || null == LocalAttribute.getToken().getLocale())
        {
            l = CommonHelper.getDefaultLocal();
        }
        else
        {
            l = LocalAttribute.getToken().getLocale();
        }
        String inName = "";
        try
        {
            inName = DictLangService.getDictName(lang, null, l);
        }
        catch(Exception e)
        {
            debug.log(e);
        }
        return inName;
    }

    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }
}
