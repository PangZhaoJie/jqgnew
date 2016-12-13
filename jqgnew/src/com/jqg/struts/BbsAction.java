package com.jqg.struts;

import com.jqg.pojo.Asktype;
import com.jqg.pojo.Inbox;
import com.jqg.pojo.Investstrategy;
import com.jqg.pojo.Usercomment;
import com.jqg.pojo.Uservip;
import com.jqg.service.AsktypeService;
import com.jqg.service.InveststrategyService;
import com.jqg.service.UsercommentService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.AsktypeServiceImpl;
import com.jqg.service.impl.InveststrategyServiceImpl;
import com.jqg.service.impl.UsercommentServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
/**
 * 论坛
 * @author Administrator
 *
 */
public class BbsAction extends BaseAction
{
  private Investstrategy investstrategy;
  private List<Investstrategy> investstrategyList;
  private List<Investstrategy> typeList;
  private List<Asktype> askTypeList;
  private Integer askId;
  private String userId;
  private String askTypeId;
  private Integer askTypeFlag = Integer.valueOf(0);
  private int total = 0;
  private int currentPage;
  private int pageSize = 3;
  private int totalPage;
  private Usercomment usercomment;
  private List<Usercomment> usercommentList;
  private Integer pageNum;
  private InveststrategyService investmentlevelService = new InveststrategyServiceImpl();
  private UservipService uservipService = new UservipServiceImpl();
  private AsktypeService asktypeService = new AsktypeServiceImpl();
  private UsercommentService usercommentService = new UsercommentServiceImpl();
  private List<Inbox> inboxs;
  private String[][] borrow = { { "3", "3" }, { "2", "2" } };
/**
 * 进入论坛首页
 * @return
 * @throws Exception
 */
  public String toBbsIndex()
    throws Exception
  {
    this.investstrategyList = this.investmentlevelService.findInveststrategyByDate(-1, "from Investstrategy i ORDER BY i.askDate DESC ");
    this.typeList = this.investmentlevelService.findInveststrategyByType();
    return "success";
  }
/**
 * 查看帖子详情
 * @return
 * @throws Exception
 */

  public String toBbsView()
    throws Exception
  {
    this.investstrategy = this.investmentlevelService.findInveststrategyById(this.askId);
    this.investstrategy.setAskUserName(this.investstrategy.getUservip().getUserName());
    List list = this.usercommentService.findUsercommentByUserId(this.askId, Integer.valueOf(0), Integer.valueOf(0));
    this.total = list.size();
    if (this.currentPage == 0) {
      this.currentPage = 1;
    }
    if (this.total == 0)
      this.pageNum = Integer.valueOf(1);
    else if (this.total % this.pageSize == 0)
      this.pageNum = Integer.valueOf(this.total / this.pageSize);
    else {
      this.pageNum = Integer.valueOf(this.total / this.pageSize + 1);
    }

    String sql = "select * from Usercomment u where u.askId=" + this.askId + " order by u.commentDate " + 
      "LIMIT " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    this.usercommentList = this.usercommentService.findUsercommentByPage(sql);
    return "success";
  }
/**
 * 新增帖子评论
 * @return
 * @throws Exception
 */
  public String toCommentAdd()
    throws Exception
  {
    this.usercomment.setCommentDate(new Timestamp(new Date().getTime()));
    this.usercomment.setUservip(this.uservipService.findUservipByUserid(24));
    this.usercomment.setInveststrategy(this.investmentlevelService.findInveststrategyById(this.askId));
    this.usercommentService.addUsercomment(this.usercomment);

    return "success";
  }
/**
 * 新增帖子
 * @return
 * @throws Exception
 */

  public String toBbsAdd() throws Exception {
    this.investstrategy.setAskDate(new Timestamp(new Date().getTime()));
    this.investstrategy.setUservip(this.uservipService.findUservipByUserid(24));
    this.investstrategy.setAsktype(this.asktypeService.findAsktypeById(Integer.valueOf(Integer.parseInt(this.askTypeId))));
    this.investmentlevelService.addInveststrategy(this.investstrategy);
    return "success";
  }

  public String toType() throws Exception {
    this.askTypeList = this.asktypeService.findAsktypes();
    return "success";
  }
/**
 * 进入帖子列表
 * @throws Exception
 */
  public void toBbsList()
    throws Exception
  {
    String sql1 = "";
    String sql = "";
    if (this.askTypeFlag.intValue() == 0)
      sql1 = "from Investstrategy i  ORDER BY i.askDate DESC";
    else {
      sql1 = "from Investstrategy i where i.asktype.askTypeId=" + this.askTypeFlag + " ORDER BY i.askDate DESC";
    }
    this.investstrategyList = this.investmentlevelService.findInveststrategyByDate(this.askTypeFlag.intValue(), sql1);
    int totalRecord = this.investstrategyList.size();
    this.total = totalRecord;
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
    if (this.askTypeFlag.intValue() == 0)
      sql = "SELECT * FROM Investstrategy t   ORDER BY t.askDate DESC LIMIT " + 
        (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    else {
      sql = "SELECT * FROM Investstrategy t  where t.askTypeId=" + this.askTypeFlag + 
        " ORDER BY t.askDate DESC LIMIT " + (this.currentPage - 1) * this.pageSize + "," + this.pageSize;
    }
    List<Investstrategy> investstrategypage = null;
    try {
      investstrategypage = this.investmentlevelService.findInveststrategyByPage(sql);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    StringBuffer str = new StringBuffer();
    str.append("{\"totalPage\":\"" + this.totalPage + "\",");
    str.append("\"total\":\"" + this.total + "\",");
    str.append("\"currentPage\":\"" + this.currentPage + "\",");
    str.append("\"askType\":\"" + this.askTypeFlag + "\",");
    str.append("\"jsonRoot\":[");
    for (Investstrategy investstrategy : investstrategypage) {
      str.append("{\"askId\":\"" + investstrategy.getAskId() + "\",");
      str.append("\"userName\":\"" + investstrategy.getUservip().getUserName() + "\",");
      str.append("\"askTitle\":\"" + investstrategy.getAskTitle() + "\",");
      str.append("\"askDate\":\"" + investstrategy.getAskDate() + "\",");
      str.append("\"commentCount\":\"" + investstrategy.getCommentCount() + "\",");
      str.append("\"clickCount\":\"" + investstrategy.getClickCount() + "\"},");
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

  public String toTest()
  {
    System.out.println(this.borrow[0][0]);
    return "success";
  }

  public List<Inbox> getInboxs()
  {
    return this.inboxs;
  }
  public void setInboxs(List<Inbox> inboxs) {
    this.inboxs = inboxs;
  }
  public String[][] getBorrow() {
    return this.borrow;
  }
  public void setBorrow(String[][] borrow) {
    this.borrow = borrow;
  }
  public Integer getPageNum() {
    return this.pageNum;
  }
  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }
  public Usercomment getUsercomment() {
    return this.usercomment;
  }
  public void setUsercomment(Usercomment usercomment) {
    this.usercomment = usercomment;
  }
  public String getUserId() {
    return this.userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getAskTypeId() {
    return this.askTypeId;
  }
  public void setAskTypeId(String askTypeId) {
    this.askTypeId = askTypeId;
  }
  public Integer getAskTypeFlag() {
    return this.askTypeFlag;
  }

  public void setAskTypeFlag(Integer askTypeFlag) {
    this.askTypeFlag = askTypeFlag;
  }

  public Integer getAskId() {
    return this.askId;
  }

  public void setAskId(Integer askId) {
    this.askId = askId;
  }

  public List<Investstrategy> getTypeList() {
    return this.typeList;
  }

  public void setTypeList(List<Investstrategy> typeList) {
    this.typeList = typeList;
  }

  public Investstrategy getInveststrategy() {
    return this.investstrategy;
  }
  public void setInveststrategy(Investstrategy investstrategy) {
    this.investstrategy = investstrategy;
  }
  public List<Investstrategy> getInveststrategyList() {
    return this.investstrategyList;
  }
  public void setInveststrategyList(List<Investstrategy> investstrategyList) {
    this.investstrategyList = investstrategyList;
  }
  public int getTotal() {
    return this.total;
  }
  public void setTotal(int total) {
    this.total = total;
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
  public List<Asktype> getAskTypeList() {
    return this.askTypeList;
  }
  public void setAskTypeList(List<Asktype> askTypeList) {
    this.askTypeList = askTypeList;
  }
  public List<Usercomment> getUsercommentList() {
    return this.usercommentList;
  }
  public void setUsercommentList(List<Usercomment> usercommentList) {
    this.usercommentList = usercommentList;
  }
}