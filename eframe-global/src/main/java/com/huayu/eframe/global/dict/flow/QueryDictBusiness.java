package com.huayu.eframe.global.dict.flow;

import com.huayu.eframe.flow.AbstractExecuteBusiness;
import com.huayu.eframe.flow.BusinessParameter;
import com.huayu.eframe.global.dict.common.DictionaryUtils;
import com.huayu.eframe.global.dict.entity.cache.DictEntityCache;
import com.huayu.eframe.global.dict.reader.DictDetail;
import com.huayu.eframe.global.dict.reader.DictLangService;
import com.huayu.eframe.global.dict.reader.DictionaryService;
import com.huayu.eframe.server.common.CommonHelper;
import com.huayu.eframe.server.context.LocalAttribute;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
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

    @Autowired
    private DictEntityCache dictEntityCache;

    @Override
    public void before(BusinessParameter param)
    {
        String code = param.getRequest();
        if(StringUtils.isNullOrEmpty(code))
        {
            throw new IFPException();
        }
    }

    @Override
    public void execute(BusinessParameter param)
    {
        String code = param.getRequest();
        List<Dict> dictFromEntity = dictEntityCache.queryDictByDictCode(code);
        if(null == dictFromEntity)
        {
            dictFromEntity = getDictFromXML(code);
        }
        param.addParameter(RESULT, dictFromEntity);
    }

    private List<Dict> getDictFromXML(String code)
    {
        List<DictDetail> result = dictionaryService.getDict(code);

        List<Dict> dicts = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(result))
        {
            for (DictDetail dictDetail : result)
            {
                if(null == dictDetail || StringUtils.equalStringNoCareUpperAndLower(dictDetail.getDisplay(),"false"))
                {
                    continue;
                }
                Dict dict = new Dict();
                dict.setKey(dictDetail.getCode());

                String value = DictionaryUtils.buildDictName(dictDetail);
                dict.setName(value);
                dicts.add(dict);
            }
        }
        return dicts;
    }


    @Override
    protected Object tidyData(BusinessParameter param)
    {
        return param.getParameter(RESULT);
    }

}
