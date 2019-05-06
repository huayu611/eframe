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

    public static void main(String[] args) throws Exception
    {
        String sessionKey = "NuUojFKl3t0tcrdsPjvl+A==";
        String encryptedData = "Hi2voV8UOuFqKzyAdNr1tUzG9+TP6yk1f1fvGXzixfYCCf2CehOY/ubiLK6a4zqN5pR4VBG4ZEMmyx0aHLaE8Qq23YBr6do1//PJeA8Xe4cp7qwLCx0zZv495rISPoqxc7BVAD8laNxNvot8A1ogPFKvfXS5j9EmuylYfhhRivbbwZ8HBZlsjpXQbCZ6IdHA5yWA7Zs0U6L0WtBzLuk+rnjHDUgQoHezDmbT8y8nsKFxKOC7dGQDWLigYm80ZoAGqr7APVsYq79m52EnBZzYXb8QcVOT/g6sAA4fCNQ7NKCc9Ow3Il4v0jm6223Lqfb/B6s2msuuXR/cbrOjEOTO+3Aadgj9uZHR8lQ4sggnAoLEJdYSxm81isepmYbNByfSSP4PVwynEfNSASGmltx+8Z+uVEZn1L4x9hq15+UkoPpqk0WKcHTh1npR/tkmUxO8OrhhIkxefnb8eoEgRDXnYI4myNWBfLw5pFv16MAPeyo=";
        String iv = "dmaptaQz856OTZreR03gTw==";


        String a = aesCbcDecodeByString(encryptedData,sessionKey,iv);
        System.out.println(a);
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
