package com.huayu.eframe.global.dict.reader;

import com.huayu.eframe.server.log.LogDebug;
import com.huayu.eframe.server.tool.basic.XmlUtils;
import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/1/7.
 * 数据字典采用XML和properties文件双重控制 ，XML配置数据字典，而properites配置字典描述
 * 可以通过_dict_ext.xml覆盖和*_dict.xml配置
 * <dicts>
 * <dict code="DICTA">
 * <value code="0" lang="DICT_VALUE1"></value>
 * <value code="1" lang="DICT_VALUE1"></value>
 * </dict>
 * </dicts>
 * 如果覆盖需要重写整个dict
 */

@Service
public class DictionaryService implements InitializingBean
{
    private static final LogDebug debug = new LogDebug(DictionaryService.class);

    @Value(value = "classpath*:/META-INF/config/dict/*_dict.xml")
    private Resource[] resource;


    @Value(value = "classpath*:/META-INF/config/dict/*_dict_ext.xml")
    private Resource[] extResource;

    private Map<String, List<DictDetail>> dictMap;


    public DictionaryService()
    {
        dictMap = new HashMap<>();
    }

    public void read(Resource[] requestResource)
    {
        if (requestResource.length == 0)
        {
            return;
        }

        for (Resource res : requestResource)
        {
            try
            {
                if (null != res)
                {
                    InputStream input = res.getInputStream();
                    DictBO result = XmlUtils.paraseXMLFiles(DictBO.class, input);
                    debug.log(result);
                    putCache(result);
                }
            }
            catch (Exception e)
            {
                System.out.print("System paramter load file and file name is " + res.getFilename());
            }
        }
    }

    private void putCache(DictBO dictBO)
    {
        List<Dictionaries> result = dictBO.getDictionaries();
        if (CollectionUtils.isEmpty(result))
        {
            return;
        }
        for (Dictionaries d : result)
        {
            String key = d.getCode();
            List<DictDetail> obj = d.getDictDetail();
            dictMap.put(key, obj);
        }
    }

    public List<DictDetail> getDict(String code)
    {
        List<DictDetail> result = dictMap.get(code);
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        read(resource);

        read(extResource);
    }
}
