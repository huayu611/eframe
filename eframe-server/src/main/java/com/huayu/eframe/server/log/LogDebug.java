package com.huayu.eframe.server.log;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.huayu.eframe.server.tool.basic.ObjectUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogDebug
{
    private Debug debug;

    private Class clazz;

    public LogDebug(Class clazz)
    {
        debug = new Debug(clazz);
        this.clazz = clazz;
    }

    public void beginLog(Object... objects)
    {
        try {

            StringBuilder logBuild = new StringBuilder();
            StackTraceElement[] element = Thread.currentThread().getStackTrace();
            String methodName = element[2].getMethodName();
            int lineNumber = element[2].getLineNumber();
            logBuild.append("[BEGIN]");
            logBuild.append(clazz.getName()).append(".").append(methodName).append(":").append(lineNumber);
            if (objects.length > 0) {
                logBuild.append(":{").append(formatStrings(objects)).append("}");
            }
            debug.debug(logBuild.toString());
        } catch (Exception e) {
        }
    }

    public void endLog(Object... objects)
    {
        try {


            StringBuilder logBuild = new StringBuilder();
            StackTraceElement[] element = Thread.currentThread().getStackTrace();
            String methodName = element[2].getMethodName();
            int lineNumber = element[2].getLineNumber();
            logBuild.append("[END]");
            logBuild.append(clazz.getName()).append(".").append(methodName).append(":").append(lineNumber);
            if (objects.length > 0) {
                logBuild.append(":{").append(formatStrings(objects)).append("}");
            }
            debug.debug(logBuild.toString());
        } catch (Exception e) {
        }
    }

    public void log(Object... objects)
    {
        try {

            StringBuilder logBuild = new StringBuilder();
            StackTraceElement[] element = Thread.currentThread().getStackTrace();
            String methodName = element[2].getMethodName();
            int lineNumber = element[2].getLineNumber();

            logBuild.append("[IN]");
            logBuild.append(clazz.getName()).append(".").append(methodName).append(":").append(lineNumber);

            if (objects.length > 0) {
                logBuild.append(":{").append(formatStrings(objects)).append("}");
            }
            debug.debug(logBuild.toString());
        } catch (Exception e) {
        }
    }

    public void errorLog(Object... objects)
    {
        try {

            StringBuilder logBuild = new StringBuilder();
            StackTraceElement[] element = Thread.currentThread().getStackTrace();
            String methodName = element[2].getMethodName();
            int lineNumber = element[2].getLineNumber();
            String threadName = Thread.currentThread().getName();
            logBuild.append("[").append(threadName).append("]");
            logBuild.append("[ERROR]");
            logBuild.append(clazz.getName()).append(".").append(methodName).append(":").append(lineNumber);

            if (objects.length > 0) {
                logBuild.append(":{").append(formatStrings(objects)).append("}");
            }
            debug.error(logBuild.toString());
        } catch (Exception e) {
        }
    }

    public void printErr(Throwable e)
    {
        if (null == e) {
            debug.info("No Err For Print");
        }
        String trace = ObjectUtils.getTrace(e);
        debug.error(trace);

    }

    private String formatStrings(Object... obj)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < obj.length; i++) {
            sb.append("(").append(formatString(obj[i])).append(")");
            if (i + 1 != obj.length) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private String formatString(Object obj)
    {
        if (null == obj) {
            return "null";
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonlist = "";
        try {
            jsonlist = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return obj.toString();
        }
        return jsonlist;
    }

}
