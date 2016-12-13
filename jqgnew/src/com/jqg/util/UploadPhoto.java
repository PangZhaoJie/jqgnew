 package com.jqg.util;
 
 import java.io.File;
 import java.io.IOException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import javax.servlet.ServletContext;
 import org.apache.commons.io.FileUtils;
 import org.apache.struts2.ServletActionContext;
 
 public class UploadPhoto
 {
   public static String newpicture(String picFileName, File imgFile)
     throws IOException
   {
     Date date = new Date(System.currentTimeMillis());
     String strDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
     int random = (int)(Math.random() * 99.0D);
     String imageName = strDate + random;
     picFileName = imageName + picFileName.substring(picFileName.indexOf("."));
     String realPath = ServletActionContext.getServletContext().getRealPath("/images/new");
     File saveFile = new File(new File(realPath), picFileName);
 
     if (!saveFile.getParentFile().exists()) {
       saveFile.getParentFile().mkdirs();
     }
 
     if (!saveFile.exists()) {
       FileUtils.copyFile(imgFile, saveFile);
     }
     return "/uploadImg/" + picFileName;
   }
 
   public static String LoadImageToServer(String picFileName, File imgFile) throws IOException
   {
     Date date = new Date(System.currentTimeMillis());
     String strDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
     int random = (int)(Math.random() * 99.0D);
     String imageName = strDate + random;
 
     picFileName = imageName + picFileName.substring(picFileName.indexOf("."));
     String realPath = ServletActionContext.getServletContext().getRealPath("/uploadImg");
     File saveFile = new File(new File(realPath), picFileName);
 
     if (!saveFile.getParentFile().exists()) {
       saveFile.getParentFile().mkdirs();
     }
 
     if (!saveFile.exists()) {
       FileUtils.copyFile(imgFile, saveFile);
     }
     return "/uploadImg/" + picFileName;
   }
 }

