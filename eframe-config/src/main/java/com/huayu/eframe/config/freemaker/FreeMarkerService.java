package com.huayu.eframe.config.freemaker;

import com.huayu.eframe.server.log.LogDebug;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Created by Leo on 2019/3/14.
 */
@Service
public class FreeMarkerService
{

    private static final LogDebug debug = new LogDebug(FreeMarkerService.class);

    private static final String FILE_SUFFIX = ".ftl";
    @Autowired
    private FreeMarkerConfigurer freemarkerConfig;

    public String getFreeMarkerText(String ftl, Object model)
    {
        try
        {
            Configuration config = freemarkerConfig.getConfiguration();
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

}
