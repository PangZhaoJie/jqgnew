 package com.jqg.util;
 
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.FileInputStream;
 import java.io.IOException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public class ExcelDownload
 {
   public static void download(HttpServletRequest request, HttpServletResponse response, String fileUrl, String fileName)
   {
     BufferedInputStream bis = null;
     BufferedOutputStream bos = null;
     try
     {
       fileUrl = new String(fileUrl.getBytes("utf-8"), "utf-8");
       fileName = new String(fileName.getBytes("utf-8"), "utf-8");
       response.setContentType("application/x-msdownload");
       response.setCharacterEncoding("utf-8");
       response.setHeader("Content-disposition", "attachment; filename=" + 
         fileName);
 
       bis = new BufferedInputStream(new FileInputStream(
         fileUrl));
       bos = new BufferedOutputStream(response.getOutputStream());
 
       byte[] buff = new byte[2048];
 
       int i = 0;
       int bytesRead;
       while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
       {
         bos.write(buff, 0, bytesRead);
         i++;
       }
       bos.flush();
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       if (bis != null) {
         try {
           bis.close();
         }
         catch (IOException e) {
           e.printStackTrace();
         }
         bis = null;
       }
       if (bos != null)
       {
         try {
           bos.close();
         }
         catch (IOException e) {
           e.printStackTrace();
         }
         bos = null;
       }
     }
   }
 }

