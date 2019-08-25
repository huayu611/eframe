package com.huayu.eframe.global.dict.common;

import com.huayu.eframe.flow.valid.ValidBeanParamDefined;
import com.huayu.eframe.global.constants.GlobalErrorCode;
import com.huayu.eframe.global.dict.entity.cache.DictEntityCache;
import com.huayu.eframe.global.dict.flow.Dict;
import com.huayu.eframe.global.dict.reader.DictDetail;
import com.huayu.eframe.global.dict.reader.DictionaryService;
import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.service.exception.IFPException;
import com.huayu.eframe.server.tool.basic.StringUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Leo on 2019/3/10.
 */
@Service("_dictionary")
public class DictionaryValidRequest implements ValidBeanParamDefined
{
    private static final LogDebug debug = new LogDebug(DictionaryValidRequest.class);

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictEntityCache dictEntityCache;

    @Override
    public void process(Field field, Object value, Object request, String parameter)
    {
        String valueString = StringUtils.getString(value);
        if (StringUtils.isNullOrEmpty(valueString) || StringUtils.isNullOrEmpty(parameter))
        {
            return;
        }
        List<Dict> dicts = dictEntityCache.queryDictByDictCode(parameter);
        if(null != dicts)
        {
            boolean right = false;
            for(Dict dict : dicts)
            {
                if(StringUtils.equalString(dict.getName(),valueString))
                {
                    right = true;
                    break;
                }
            }
            if(!right)
            {
                debug.log(valueString);
                throw new IFPException(GlobalErrorCode.VALUE_IN_REQUEST_NOT_SUIT_DICT, "value not in dictionary", new String[]{field.getName()});
            }
            return;
        }
        List<DictDetail> result = dictionaryService.getDict(parameter);
        if (!contains(result, valueString))
        {
            debug.log(valueString);
            throw new IFPException(GlobalErrorCode.VALUE_IN_REQUEST_NOT_SUIT_DICT, "value not in dictionary", new String[]{field.getName()});
        }
    }

    private boolean contains(List<DictDetail> dictValue, String paramValue)
    {

        if (CollectionUtils.isEmpty(dictValue))
        {
            return false;
        }
        for (DictDetail dictDetail : dictValue)
        {
            if (StringUtils.equalString(dictDetail.getCode(), paramValue))
            {
                return true;
            }
        }
        return false;
    }
}
