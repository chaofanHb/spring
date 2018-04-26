package com.cn.util;

import com.cn.okhttp.Exceptions;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class DecriptUtil {

    //每次byte与String转换需要Base64.encodeBase64，Base64.decodeBase64  进行填充或缩减
    public static void main(String[] args) {
        try {
            byte[] salt=Hex.decodeHex("30dc400b03fd293b".toCharArray());


            String s= Hex.encodeHexString(digest("1234".getBytes(), "SHA-1", salt, 1024));
            System.out.println(s);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

    }

    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest e = MessageDigest.getInstance(algorithm);
            if(salt != null) {
                e.update(salt);
            }

            byte[] result = e.digest(input);

            for(int i = 1; i < iterations; ++i) {
                e.reset();
                result = e.digest(result);
            }

            return result;
        } catch (GeneralSecurityException var7) {
            throw Exceptions.unchecked(var7);
        }
    }

    /**
     * @author：罗国辉
     * @date： 2015年12月17日 上午9:16:22
     * @description： AES加密
     * @parameter： str：待加密字符串，secretKeyBase：用于生成密钥的基础字符串
     * @return： 加密字节数组
     **/
    public static byte[] encryptAES(String str, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = str.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author：罗国辉
     * @date： 2015年12月17日 上午9:16:22
     * @description： AES解密
     * @parameter： strByteArray：待解密字节数组，
     * @parameter： secretKeyBase：用于生成密钥的基础字符串， 需要注意的是EAS是对称加密，所以secretKeyBase在加密解密时要一样的
     * @return： 解密后字符串
     **/
    public static String decryptAES(byte[] strByteArray, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            String result = new String(cipher.doFinal(strByteArray), "UTF-8");
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author：罗国辉
     * @date： 2015年12月17日 上午9:24:43
     * @description： SHA、SHA1加密
     * @parameter： str：待加密字符串
     * @return： 加密串
     **/
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author：罗国辉
     * @date： 2015年12月17日 上午9:24:43
     * @description： MD5加密
     * @parameter： str：待加密字符串
     * @return： 加密串
     **/
    public static String MD5(String str) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author：罗国辉
     * @date： 2015年12月17日 上午9:24:43
     * @description： MD5加密 返回二进制
     * @parameter： str：待加密字符串
     * @return： 加密二进制
     **/
    public static byte[] MD5ToByteArray(String str) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            return md;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}