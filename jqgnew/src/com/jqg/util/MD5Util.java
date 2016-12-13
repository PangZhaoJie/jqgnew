 package com.jqg.util;
 
 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;
 
 public class MD5Util
 {
   public static String md5(String password)
     throws NoSuchAlgorithmException
   {
     MessageDigest md = MessageDigest.getInstance("MD5");
     md.update(password.getBytes());
     byte[] b = md.digest();
 
     StringBuffer buf = new StringBuffer("");
     for (int offset = 0; offset < b.length; offset++) {
       int i = b[offset];
       if (i < 0) i += 256;
       if (i < 16)
         buf.append("0");
       buf.append(Integer.toHexString(i));
     }
 
     String pwd = buf.toString().substring(8, 24);
 
     return pwd;
   }
 }

