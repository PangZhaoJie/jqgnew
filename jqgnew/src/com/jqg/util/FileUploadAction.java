 package com.jqg.util;
 
 import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.jqg.struts.BaseAction;
 
 public class FileUploadAction extends BaseAction
 {
   private File myFile;
   private String myFileFileName;
   private String imageFileName;
   private String result;
   private int width;
   private int height;
 
   public int getWidth()
   {
     return this.width;
   }
 
   public int getHeight() {
     return this.height;
   }
 
   public String getImageFileName()
   {
     return this.imageFileName;
   }
 
   public String getResult() {
     return this.result;
   }
 
   public void setMyFile(File myFile) {
     this.myFile = myFile;
   }
 
   public void setMyFileFileName(String myFileFileName) {
     this.myFileFileName = myFileFileName;
   }
 
   private static void copy(File src, File dst)
   {
     try
     {
       InputStream inputStream = new BufferedInputStream(new FileInputStream(src));
       OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dst));
       int data;
       while ((data = inputStream.read()) > -1)
       {
         outputStream.write(data);
       }
       outputStream.close();
       inputStream.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   private static String getExtention(String myFileFileName)
   {
     int pos = myFileFileName.lastIndexOf(".");
     return myFileFileName.substring(pos);
   }
 /**
  * ÉÏ´«
  */
   public String execute()
     throws Exception
   {
     String extention = getExtention(this.myFileFileName);
     if ((extention.contains("jpg")) || (extention.contains("png")) || (extention.contains("gif")))
     {
       this.imageFileName = (new Date().getTime() + extention);
       File imageFile = 
         new File(ServletActionContext.getServletContext().getRealPath("images/new") + "/" + this.imageFileName);
       copy(this.myFile, imageFile);
       try
       {
         BufferedImage image = ImageIO.read(this.myFile);
         this.width = image.getWidth();
         this.height = image.getHeight();
 
         Thread.sleep(300L);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
       this.result = "success";
     }
     else
     {
       this.result = "error";
     }
     return "success";
   }
 }

