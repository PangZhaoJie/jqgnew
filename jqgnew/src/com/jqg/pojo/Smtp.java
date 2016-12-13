 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Smtp
   implements Serializable
 {
   private Integer smtpid;
   private String smtpemail;
   private String smtppassword;
   private String smtpsever;
 
   public Smtp()
   {
   }
 
   public Smtp(Integer smtpid)
   {
     this.smtpid = smtpid;
   }
 
   public Smtp(Integer smtpid, String smtpemail, String smtppassword, String smtpsever)
   {
     this.smtpid = smtpid;
     this.smtpemail = smtpemail;
     this.smtppassword = smtppassword;
     this.smtpsever = smtpsever;
   }
 
   public Integer getSmtpid()
   {
     return this.smtpid;
   }
 
   public void setSmtpid(Integer smtpid) {
     this.smtpid = smtpid;
   }
 
   public String getSmtpemail() {
     return this.smtpemail;
   }
 
   public void setSmtpemail(String smtpemail) {
     this.smtpemail = smtpemail;
   }
 
   public String getSmtppassword() {
     return this.smtppassword;
   }
 
   public void setSmtppassword(String smtppassword) {
     this.smtppassword = smtppassword;
   }
 
   public String getSmtpsever() {
     return this.smtpsever;
   }
 
   public void setSmtpsever(String smtpsever) {
     this.smtpsever = smtpsever;
   }
 }

