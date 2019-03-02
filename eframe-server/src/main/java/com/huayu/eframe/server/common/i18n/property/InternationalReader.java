package com.huayu.eframe.server.common.i18n.property;

import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2019/2/28.
 */
public abstract class InternationalReader implements InitializingBean
{

    private String[] allProperties;

    public void read(Resource[] requestResource)
    {
        List<String> fileNameList = new ArrayList<>();
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

                   String filename = res.getFilename();
                   String fileNameWithoutSuffix = StringUtils.removeLastString(filename,".properties");
                    fileNameList.add(getPath() + fileNameWithoutSuffix);
                }
            }
            catch(Exception e)
            {
                System.out.print("System parameter load file and file name is " + res.getFilename());
            }
        }
        allProperties = new String[fileNameList.size()];

        for(int i =0;i< fileNameList.size();i++)
        {
            allProperties[i] = fileNameList.get(i);
        }
    }

    public String[] getAllResource()
    {
            return allProperties;
    }

    protected abstract String getPath();


}
