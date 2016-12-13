package com.jqg.util;

import java.security.MessageDigest;

/*
 * Created on 2002-4-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author ANDYLIU
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/************************************************
 MD5 算法的Java Bean
 @author:Topcat Tuppin
 Last Modified:10,Mar,2001
 *************************************************/

/*******************************************************************************
 * md5 类实现了RSA Data Security, Inc.在提交给IETF 的RFC1321中的MD5 message-digest 算法〄1�7
 ******************************************************************************/

public class MD5EasyPay {

	public static MD5EasyPay instance = new MD5EasyPay();

	public static MD5EasyPay getInstance() {
		return instance;
	}

	public String getMD5ofStr(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}

	}

	public static void main(String[] args) {
		System.out.println(MD5EasyPay.getInstance().getMD5ofStr("account_dt=20105019&bill_org_id=898310079950002&buss_id=0000000002&confirm_in=3&contract_no=10081900000024&discount_at=0&ext_user_id=大排&mobile_phone=13817966995&owe_fee_at=100&put_org_id=898310079950002&req_org_id=898310079950002&req_seq_id=10081900000024&submit_time=20100819125046&tot_fee_at=100&txn_code=A10001234567890654322"));
	}

}