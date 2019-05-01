package com.huayu.eframe.server.common.sensitive;

import com.huayu.eframe.server.tool.basic.JSonUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/2/19.
 */
@Service
public class Sensitive implements InitializingBean
{
    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";

    @Autowired
    private ReadSensitiveData readSensitiveData;

    private List<String> sensitiveDate = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception
    {
        String sensData = readSensitiveData.getData();
        String[] sensAttr = StringUtils.tokenizeToStringArray(sensData, MULTI_VALUE_ATTRIBUTE_DELIMITERS);

        String ignoreData = readSensitiveData.getIgnore();
        String[] ignoreAttr = StringUtils.tokenizeToStringArray(ignoreData, MULTI_VALUE_ATTRIBUTE_DELIMITERS);


        if (sensAttr.length <= 0)
        {
            return;
        }

        List<String> ignoreList = new ArrayList<>();
        if (ignoreAttr.length > 0)
        {
            for (String a : ignoreAttr)
            {
                ignoreList.add(a);
            }
        }

        for (String s : sensAttr)
        {
            if (!ignoreList.contains(s))
            {
                sensitiveDate.add(s);
            }
        }
    }

    public List<String> getSensitiveDate()
    {
        return sensitiveDate;
    }

    public String filterJsonSensitive(String json)
    {
        return JSonUtils.replaseSens(sensitiveDate, json);
    }
}
