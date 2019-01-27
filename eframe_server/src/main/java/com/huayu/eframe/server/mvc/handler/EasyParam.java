package com.huayu.eframe.server.mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class EasyParam
{
    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String,String> requestHeader;

    private Map<String,String[]> requestHeaders;

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public Map<String, String> getRequestHeader()
    {
        return requestHeader;
    }

    public void setRequestHeader(Map<String, String> requestHeader)
    {
        this.requestHeader = requestHeader;
    }

    public Map<String, String[]> getRequestHeaders()
    {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String[]> requestHeaders)
    {
        this.requestHeaders = requestHeaders;
    }
}
