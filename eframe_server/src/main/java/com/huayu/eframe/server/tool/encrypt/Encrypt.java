package com.huayu.eframe.server.tool.encrypt;

import com.huayu.eframe.server.tool.basic.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encrypt
{
	public static String getMD5Code(String password)
	{
		return MD5.GetMD5Code(password);
	}


	public static String encodeBase64(String value)
	{
		if(StringUtils.isNullOrEmpty(value))
		{
			return "";
		}

		String encrypt = Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
		return encrypt;
	}

	public static String decodeBase64(String value)
	{
		if(StringUtils.isNullOrEmpty(value))
		{
			return "";
		}

		String decode=new String(Base64.getDecoder().decode(value),StandardCharsets.UTF_8);
		return decode;
	}
}
