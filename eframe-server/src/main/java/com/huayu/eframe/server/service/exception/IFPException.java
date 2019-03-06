package com.huayu.eframe.server.service.exception;

import java.util.Locale;


public class IFPException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String[] param;

	private String errorMsg;

	private String errorInfo;

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public String[] getParam()
	{
		return param;
	}

	public void setParam(String[] param)
	{
		this.param = param;
	}

	public String getErrorMsg()
	{
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}

	public IFPException()
	{
		this.errorCode = ErrorCode.UNKNOW_ERROR;
	}

	public IFPException(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public IFPException(String errorCode, String[] array)
	{
		this.param = array;
	}

	public IFPException(String errorCode, String message, String[] array)
	{
		this.errorCode = errorCode;
		this.param = array;
		this.errorMsg = message;
	}

	public IFPException(String errorCode, String message)
	{
		this.errorCode = errorCode;
		this.errorMsg = message;
	}

	public IFPException(Throwable th)
	{
		this.errorCode = ErrorCode.UNKNOW_ERROR;
		this.errorMsg = th.getMessage();
	}

	public String toString()
	{

		String code = errorCode + ".SOLUTION";

		String exec = null;

		try {
			exec = ExceptionCacheService.getErrorInfo(code, param, null);
		}
		catch(Exception e)
		{
			return errorMsg;
		}
		return exec;
	}

	public String getErrorInfo(Locale locale)
	{

		String code = errorCode + ".ERRORDESC";

		String exec = null;

		try {
			exec = ExceptionCacheService.getErrorInfo(code, param, locale);
		}
		catch(Exception e)
		{
			return errorMsg;
		}
		return exec;
	}

}
