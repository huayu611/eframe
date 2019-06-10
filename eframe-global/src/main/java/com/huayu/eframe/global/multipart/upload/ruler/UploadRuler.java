package com.huayu.eframe.global.multipart.upload.ruler;

/**
 * Created by Leo on 2019/3/30.
 */
public interface UploadRuler
{

    String getUploadType();


    default long getSize()
    {
        return 2 * 1024 * 1024;
    }


    default String[] getFileType()
    {
        return new String[]{".+?"};
    }


    default String downLoadPath()
    {
        return "/eframe/multipart-manager/download/";
    }

    //仅对多文件上传有效，验证文件本批量最大个数
    default int getFileNumber(){return 9;}

    //文件系统的URL，如果配置，会自动同步到文件系统服务器上。并且返回httpPath给客户端
    //PS:暂不实现，同步文件服务器有很多方法，比如 http,ftp，如果有实际情况会添加功能。
    default String getFileSystemUrl(){return "";}

    //下载http前缀
    default String getFileHttpLink(){return "";}

    ;
}
