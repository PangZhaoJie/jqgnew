/**  
 * @Project: jqgnew
 * @Title: Message.java
 * @Package com.jqg.pojo
 * @date 2015-1-9 ����4:24:34
 * @Copyright: 2015 
 */
package com.jqg.pojo;

import java.io.Serializable;

/**
 * 
 * ������Message
 * ���ܣ�
 * ��ϸ��
 * ���ߣ����е�(caozhongde)
 * �汾��1.0
 * ���ڣ�2015-1-9 ����4:24:34
 *
 */
public class Message {
    private String  msc;
    private String  url;
    private String  time;
	public String getMsc() {
		return msc;
	}
	public void setMsc(String msc) {
		this.msc = msc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
    
}
