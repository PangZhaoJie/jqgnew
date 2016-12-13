 package com.jqg.util;
 
 import javax.mail.Authenticator;
 import javax.mail.PasswordAuthentication;
 
 public class Mail extends Authenticator
 {
   private String userName;
   private String password;
 
   public Mail(String username, String password)
   {
     this.userName = username;
 
     this.password = password;
   }
 
   public PasswordAuthentication getPasswordAuthentication()
   {
     return new PasswordAuthentication(this.userName, this.password);
   }
 
   public String getUserName()
   {
     return this.userName;
   }
 
   public void setUserName(String userName) {
     this.userName = userName;
   }
 
   public String getPassword() {
     return this.password;
   }
 
   public void setPassword(String password) {
     this.password = password;
   }
 }

