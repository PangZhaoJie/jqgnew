 package com.jqg.util;

 import java.security.MessageDigest;

 public class md5 {

 	public String getMD5ofStr(String str){
 		return this.getMD5ofStr(str,"utf-8");
 	}
 	
 	public String getMD5ofStr(String str, String encode) {
 		try{
 		MessageDigest md5 = MessageDigest.getInstance("MD5");
 		md5.update(str.getBytes(encode));
 		byte[] digest = md5.digest();

 		StringBuffer hexString = new StringBuffer();
 		String strTemp;
 		for (int i = 0; i < digest.length; i++) {
 			// byteVar &
 			// 0x000000FF�������ǣ����digest[i]�Ǹ�����������ǰ��24���㣬����byte���Ͳ���Ӱ�졣
 			// (...) | 0xFFFFFF00�������ǣ����digest[i]������������ǰ24λΪһ��
 			// ����toHexString���һ��С�ڵ���15��byte���͵�ʮ������ʱ�������ڶ�λΪ���Ҳ��ᱻ��������������ͨ��substring�������н�ȡ�����λ���ɡ�
 			strTemp = Integer.toHexString(
 					(digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
 			hexString.append(strTemp);
 		}
 			return hexString.toString();
 		}catch(Exception e){
 			e.printStackTrace();
 			return "";
 		}

 	}


 }

