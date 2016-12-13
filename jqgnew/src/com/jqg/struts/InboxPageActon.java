package com.jqg.struts;

import com.jqg.pojo.Inbox;
import com.jqg.pojo.Uservip;
import com.jqg.service.InboxService;
import com.jqg.service.impl.InboxServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class InboxPageActon extends BaseAction
{
  private static final long serialVersionUID = 1L;
  private int currentPage;
  private int pageSize = 10;
  private int totalPage;
  private Integer inboxId;
  private InboxService inboxService = new InboxServiceImpl();
  List<Inbox> inboxs;
  private int total = 0;
  private int read = 0;
  private int unread = 0;
  private String result;
  private String ids;
/**
 * 站内信
 */
  public void list()
  {
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
    try {
      this.inboxs = this.inboxService.findInboxsByUserId(uservip.getUserId().intValue());
    }
    catch (Exception e2) {
      e2.printStackTrace();
    }

    int totalRecord = this.inboxs.size();
    this.total = totalRecord;
    this.unread = this.total;
    if (totalRecord % this.pageSize == 0)
      this.totalPage = (totalRecord / this.pageSize);
    else {
      this.totalPage = (totalRecord / this.pageSize + 1);
    }
    if (this.currentPage >= this.totalPage) {
      this.currentPage = this.totalPage;
    }
    if (this.currentPage <= 1) {
      this.currentPage = 1;
    }

    String sql = "SELECT * FROM Inbox t WHERE t.userId = " + uservip.getUserId() + 
      " ORDER BY t.inboxId DESC LIMIT " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    List<Inbox> inboxspage = null;
    try {
      inboxspage = this.inboxService.findInboxsByUserIdPage(sql);
    }
    catch (Exception e1) {
      e1.printStackTrace();
    }

    StringBuffer str = new StringBuffer();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (Inbox inbox : inboxspage) {
      Integer status = inbox.getStatus();
      if ((status != null) && 
        (status.intValue() == 1)) {
        this.read += 1;
        this.unread -= 1;
      }
    }

    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"total\":\"" + this.total + "\",");
    str.append("\"read\":\"" + this.read + "\",");
    str.append("\"unread\":\"" + this.unread + "\",");
    str.append("\"jsonRoot\":[");
    for (Inbox inbox : inboxspage) {
      Integer status = inbox.getStatus();
      String statu = "未读";
      if ((status != null) && 
        (status.intValue() == 1)) {
        statu = "已读";
      }

      str.append("{\"inboxId\":\"" + inbox.getInboxId() + "\",");
      str.append("\"userName\":\"" + inbox.getUservip().getUserName() + "\",");
      str.append("\"title\":\"" + inbox.getTitle() + "\",");
      str.append("\"sendName\":\"" + inbox.getSendName() + "\",");
      str.append("\"content\":\"" + inbox.getContent() + "\",");
      str.append("\"receiveTime\":\"" + df.format(inbox.getReceiveTime()) + "\",");
      str.append("\"status\":\"" + statu + "\"},");
    }
    str.deleteCharAt(str.lastIndexOf(","));
    str.append("]}");

    HttpServletResponse response = ServletActionContext.getResponse();
    response.setCharacterEncoding("utf-8");
    try {
      response.getWriter().print(str);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String changeStatus() throws Exception { Inbox inbox = this.inboxService.findInboxByInboxId(this.inboxId.intValue());
    inbox.setStatus(Integer.valueOf(1));
    boolean flag = this.inboxService.updateInbox(inbox);
    if (flag)
      this.result = "{\"result\":\"1\"}";
    else {
      this.result = "{\"result\":\"0\"}";
    }
    return "success"; }
/**
 *   删除站内信
 * @return
 * @throws Exception
 */
  public String delInbox() throws Exception {
    if ((this.ids != null) && (!"".equals(this.ids.trim()))) {
      String[] str = this.ids.split(",");
      for (int i = 0; i < str.length; i++) {
        Inbox inbox = this.inboxService.findInboxByInboxId(Integer.parseInt(str[i]));
        this.inboxService.deleteInbox(inbox);
      }
    }
    return "success";
  }

  public String getIds()
  {
    return this.ids;
  }
  public void setIds(String ids) {
    this.ids = ids;
  }
  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalPage() {
    return this.totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }
  public int getTotal() {
    return this.total;
  }
  public void setTotal(int total) {
    this.total = total;
  }
  public int getRead() {
    return this.read;
  }
  public void setRead(int read) {
    this.read = read;
  }
  public int getUnread() {
    return this.unread;
  }
  public void setUnread(int unread) {
    this.unread = unread;
  }
  public Integer getInboxId() {
    return this.inboxId;
  }
  public void setInboxId(Integer inboxId) {
    this.inboxId = inboxId;
  }
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
}