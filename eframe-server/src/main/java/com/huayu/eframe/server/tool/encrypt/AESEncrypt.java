package com.huayu.eframe.server.tool.encrypt;

import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Administrator on 2018/7/21.
 */
public class AESEncrypt
{

    public static final String ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static final byte[] IVPARAMETERS = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    public static final String KEY_ALGORITHM = "AES";

    public static String aesEcbEncode(String value, String keyWord)
    {

        SecretKey key = restoreSecretKey(keyWord.getBytes());
        String result = "";
        try
        {
            Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encode = cipher.doFinal(value.getBytes());
            result = Base64.getEncoder().encodeToString(encode);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static String aesEcbDecode(String decodedText, String keyWord)
    {
        try
        {
            byte[] decodedTextByte = Base64.getDecoder().decode(decodedText.getBytes());
            SecretKey key = restoreSecretKey(keyWord.getBytes());
            Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(decodedTextByte));
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static SecretKey restoreSecretKey(byte[] secretBytes)
    {
        SecretKey secretKey = new SecretKeySpec(secretBytes, KEY_ALGORITHM);
        return secretKey;
    }

    public static String AesCbcEncode(String plainText, String keyWord, String IVParameter)
    {
        try
        {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IVParameter.getBytes());
            SecretKey key = restoreSecretKey(keyWord.getBytes());
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

            byte[] binEncode = cipher.doFinal(plainText.getBytes());
            String originalString = new String(binEncode);
            return originalString;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String aesCbcDecode(byte[] plainText, byte[] keyWord, byte[] ivr)
    {

        try {

            SecretKeySpec skeySpec = new SecretKeySpec(keyWord, "AES");
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ivr);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(plainText);
            String originalString = new String(original);
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String aesCbcDecodeByString(String plainText, String keyWord, String ivr)
    {
      try
      {
          byte[] ses = new BASE64Decoder().decodeBuffer(keyWord);
          byte[] enc = new BASE64Decoder().decodeBuffer(plainText);
          byte[] i = new BASE64Decoder().decodeBuffer(ivr);
          return aesCbcDecode(enc,ses,i);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return null;

    }
}
