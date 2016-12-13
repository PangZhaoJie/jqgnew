 package com.jqg.util;
 
 import com.jqg.struts.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
 import java.io.File;
 import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
 
 public class delPicAction extends BaseAction
 {
   private String FileName;
   private String message;
 
   public String getMessage()
   {
     return this.message;
   }
   public void setFileName(String fileName) {
     this.FileName = fileName;
   }
 /**
  * ɾ��ͼƬ
  */
   public String execute() throws Exception {
     File imageFile = 
       new File(ServletActionContext.getServletContext().getRealPath("/images/new") + "/" + this.FileName);
 
     if (imageFile.exists())
     {
       imageFile.delete();
       this.message = "yes";
     }
     else
     {
       this.message = "no";
     }
     return "success";
   }
 /**
  * ɾ������ͼƬ
  * @return
  * @throws Exception
  */
   public String delpics() throws Exception {
     File imageFile = 
       new File(ServletActionContext.getServletContext().getRealPath(this.FileName));
 
     if (imageFile.exists())
     {
       imageFile.delete();
       this.message = "yes";
     }
     else
     {
       this.message = "no";
     }
     return "success";
   }
 }

