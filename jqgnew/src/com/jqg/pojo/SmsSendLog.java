package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 短信发送记录
 * 
 * @author tengzhanqing
 *
 */
public class SmsSendLog implements Serializable {
	private Integer logId;
	private Uservip uservip;
	private String tophone;
	private String content;
	private Integer status;//1：发送成功、2：发送失败
	private Timestamp sendTime;
	private String title;

	public SmsSendLog() {
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Uservip getUservip() {
		return uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public String getTophone() {
		return tophone;
	}

	public void setTophone(String tophone) {
		this.tophone = tophone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

}
