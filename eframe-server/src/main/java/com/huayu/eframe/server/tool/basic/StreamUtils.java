package com.huayu.eframe.server.tool.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StreamUtils
{
    public static InputStream coverFileToStream(File file)
    {
        try
        {
            return new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }

    public static String object2Byte(Object obj)
    {
        return null;
    }

}
