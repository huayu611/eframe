package com.huayu.eframe.config.freemaker;

import com.huayu.eframe.server.log.LogDebug;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/7/22.
 */
@Service
public class EFrameFreeMarkerConfiguration implements InitializingBean
{
    private static final  LogDebug logDebug = new LogDebug(EFrameFreeMarkerConfiguration.class);

    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    private StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();;

    private static List<String> primaryCodeCache = new ArrayList<>();

    public void putStringElement(String primaryCode, String content)
    {
        if (primaryCodeCache.contains(primaryCode))
        {
            return;
        }
        primaryCodeCache.add(primaryCode);
        stringTemplateLoader.putTemplate(primaryCode, content);
    }

    public void removeStringElement(String primaryCode)
    {
        if (!primaryCodeCache.contains(primaryCode))
        {
            return;
        }
        primaryCodeCache.remove(primaryCode);
        stringTemplateLoader.removeTemplate(primaryCode);
    }

    public StringTemplateLoader getStringTemplateLoader()
    {
        return stringTemplateLoader;
    }

    public FreeMarkerConfigurer getFreeMarkerConfigurer()
    {
        return freemarkerConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        TemplateLoader store = freemarkerConfig.getConfiguration().getTemplateLoader();
        MultiTemplateLoader mStore = null;
        if(store instanceof MultiTemplateLoader )
        {
            TemplateLoader[] templateLoader = new TemplateLoader[]{stringTemplateLoader,store};
            mStore = new MultiTemplateLoader(templateLoader);
        }
        else
        {
            TemplateLoader[] templateLoader = new TemplateLoader[]{stringTemplateLoader,store};
            mStore = new MultiTemplateLoader(templateLoader);
        }
        logDebug.log(store);
        freemarkerConfig.getConfiguration().setTemplateLoader(mStore);

    }
}
