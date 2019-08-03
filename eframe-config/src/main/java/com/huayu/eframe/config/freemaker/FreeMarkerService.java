package com.huayu.eframe.config.freemaker;

import com.huayu.eframe.server.log.LogDebug;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * Created by Leo on 2019/3/14.
 */
@Service
public class FreeMarkerService
{
    private static final LogDebug debug = new LogDebug(FreeMarkerService.class);
    private static final String FILE_SUFFIX = ".ftl";


    @Autowired
    private EFrameFreeMarkerConfiguration eFrameFreeMarkerConfiguration;

    public String getFreeMarkerText(String ftl, Object model)
    {
        try
        {
            Configuration config = eFrameFreeMarkerConfiguration.getFreeMarkerConfigurer().getConfiguration();
            if (!ftl.endsWith(FILE_SUFFIX))
            {
                ftl = ftl + FILE_SUFFIX;
            }
            Template t = config.getTemplate(ftl);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            return text;
        }
        catch (Exception e)
        {
            debug.errorLog("error in FreeMakerService");
            debug.printErr(e);
            return null;
        }

    }

    public String buildFreeMarker(String primaryCode,String source,Object model)
    {
        try
        {
            eFrameFreeMarkerConfiguration.putStringElement(primaryCode, source);
            Template t = eFrameFreeMarkerConfiguration.getFreeMarkerConfigurer().getConfiguration().getTemplate(primaryCode);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
            return text;
        }
        catch (Exception e)
        {
            debug.errorLog("error in FreeMakerService");
            debug.printErr(e);
            return null;
        }
    }
}
