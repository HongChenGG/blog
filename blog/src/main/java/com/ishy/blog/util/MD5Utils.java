package com.ishy.blog.util;

/**
 * @author 红尘
 * @Date 2020/5/15 16:05
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * @Description: MD5加密
 * @Param: 要加密的字符串
 * @Return: 加密后的字符串
 */
public class MD5Utils {


    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0){
                    i += 256;}
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) {
        System.out.println(code("A744882174"));
    }
}