<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.jqg.pojo.Uservip"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();if(path!=null && !path.equals("")){	if(path.contains("/")){		if(path.equals("/")){path = "";	}}else{	path += "/";}} 
String basePath = "";if(request.getServerPort()==80){basePath = request.getScheme()+"://"+request.getServerName()+path+"/";}else{basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";}    
  String picUrl = request.getParameter("Picurl"); 
  String  step = request.getParameter("step"); 
  String defaultPic ="image/man.GIF";
  if("3".equals(step))
    defaultPic = "User/UserHeadImage/"+picUrl;
  Uservip uservip = (Uservip) ActionContext.getContext().getSession().get("uservip");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<link rel="icon" href="<%=path %>/image/favicon/favicon.ico" type="image/x-icon" />
<title></title>
<link href="<%=path %>/css/main.css" type="text/css" rel="Stylesheet" />
    <script type="text/javascript" src="<%=path %>/js/jquery1.2.6.pack.js"></script>
    <script  type="text/javascript" src="<%=path %>/js/ui.core.packed.js" ></script>
    <script type="text/javascript" src="<%=path %>/js/ui.draggable.packed.js" ></script>
    <script type="text/javascript" src="<%=path %>/js/CutPic.js"></script>
    <script type="text/javascript">
        function Step1() {
            $("#Step2Container").hide();           
        }

        function Step2() {
            $("#CurruntPicContainer").hide();
        }
        function Step3() {
            $("#Step2Container").hide();          
       }
    </script>
</head>
<body>
   

    <div>
     <div class="left">
         <!--当前照片-->
         <div id="CurruntPicContainer">
            <div class="title"><b>当前照片</b></div>
            <div class="photocontainer">
             <c:if test="${session.uservip.photoOne=='' || session.uservip.photoOne==null}" >
                <img id="imgphoto" src="../images/yh_tx.jpg" style="border-width:0px;width: 120px; height: 120px;" />
                </c:if>
                 <c:if test="${session.uservip.photoOne!='' && session.uservip.photoOne!=null}" >
                 <img src="<%=path %>${session.uservip.photoOne}" style="border-width:0px;width: 120px; height: 120px;">
                  </c:if>
            </div>
         </div>
         <!--Step 2-->
         <div id="Step2Container">
           <div class="title"><b> 裁切头像照片</b></div>
           <div class="uploadtooltip">您可以拖动照片以裁剪满意的头像</div>                              
                   <div id="Canvas" class="uploaddiv">
                   
                            <div id="ImageDragContainer">       
                            <div id="ImageDrag"> </div>         
                                         
                            </div>
                            <div id="IconContainer">                               
                               <img id="ImageIcon" class="imagePhoto" src="<%=path+"/UploadPhoto/"+picUrl%>" style="border-width:0px;" />                                                        
                            </div>                          
                    </div>
                    <div class="uploaddiv">
                       <table>
                            <tr>
                                <td id="Min">
                                        <img alt="缩小" src="<%=path %>/image/_c.gif" onmouseover="this.src='<%=path %>/image/_c.gif';" onmouseout="this.src='<%=path %>/image/_h.gif';" id="moresmall" class="smallbig" />
                                </td>
                                <td>
                                    <div id="bar">
                                        <div class="child">
                                        </div>
                                    </div>
                                </td>
                                <td id="Max">
                                        <img alt="放大" src="<%=path %>/image/c.gif" onmouseover="this.src='<%=path %>/image/c.gif';" onmouseout="this.src='<%=path %>/image/h.gif';" id="morebig" class="smallbig" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <form action="<%=path %>/servlet/ZoomImage.servlet" method="post">
                    <input type="hidden" name="picture" value="<%=picUrl%>"/>
                    <div class="uploaddiv">
                        <input type="submit" name="btn_Image" value="保存头像" id="btn_Image" />
                    </div>           
                    <div style="display: none">
                    图片实际宽度： <input name="txt_width" type="text" value="1" id="txt_width" /><br />
                    图片实际高度： <input name="txt_height" type="text" value="1" id="txt_height" /><br />
                    距离顶部： <input name="txt_top" type="text" value="82" id="txt_top" /><br />
                    距离左边： <input name="txt_left" type="text" value="73" id="txt_left" /><br />
                    截取框的宽： <input name="txt_DropWidth" type="text" value="150" id="txt_DropWidth" /><br />
                    截取框的高： <input name="txt_DropHeight" type="text" value="150" id="txt_DropHeight" /><br />
                    放大倍数： <input name="txt_Zoom" type="text" id="txt_Zoom" />
                    <input name="userId" type="text" id="userId" value="<%=uservip.getUserId() %>" />
                   </div>  </form>
         </div>
     
     </div>
      <form name="form1" method="post" action="<%=path %>/servlet/UpLoadUserHeadImage.servlet" id="form1" enctype="multipart/form-data">
     <div class="right">
         <!--Step 1-->
         <div id="Step1Container">
            <div class="title"><b>更换照片</b></div>
            <div id="uploadcontainer">
                <div class="uploadtooltip">请选择新的照片文件，文件需小于2.5MB</div>
                <div class="uploaddiv"><input type="file" name="fuPhoto" id="fuPhoto" title="选择照片" /></div>
                <div class="uploaddiv"><input type="submit" name="btnUpload" value="上 传" id="btnUpload" /></div>
            </div>
         
         </div>
     </div>
     </form>
    </div>
    <% 
      if(null==picUrl||"".equals(picUrl))
      {%>
          <script type='text/javascript'>Step1();</script>
      <%}else if(!"".equals(picUrl)&& "2".equals(step)){
      %>
      <script type='text/javascript'>Step2();</script>
      <%}else if(!"".equals(picUrl)&& "3".equals(step)){
      %>
      <script type='text/javascript'>Step3();</script>
      <%}%>

</body>
</html>