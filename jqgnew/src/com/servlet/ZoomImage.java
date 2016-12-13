package com.servlet;

import com.jqg.pojo.Uservip;
import com.jqg.service.UservipService;
import com.jqg.service.impl.UservipServiceImpl;
import com.teetaa.util.ImageHepler;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZoomImage extends HttpServlet
{
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
    int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
    int cutTop = Integer.parseInt(request.getParameter("txt_top"));
    int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
    int dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
    int dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
    int userId = Integer.parseInt(request.getParameter("userId"));
    float imageZoom = Float.parseFloat(request.getParameter("txt_Zoom"));
    String picture = request.getParameter("picture");

    Rectangle rec = new Rectangle(cutLeft, cutTop, dropWidth, dropHeight);
    File file = new File(request.getRealPath("") + "/user/UserHeadImage/" + picture);

    saveSubImage(new File(request.getRealPath("") + "/UploadPhoto/" + picture), file, imageWidth, imageHeight, rec);
    UservipService userService = new UservipServiceImpl();
   
    boolean flag;
    try
    {
      Uservip uservip = userService.findUservipByUserid(userId);
      uservip.setPhotoOne("/user/UserHeadImage/" + picture);
      flag = userService.updateUservip(uservip);
    }
    catch (Exception e)
    {
      
      e.printStackTrace();
    }
    response.sendRedirect("../user/picture?userId=" + userId);
  }

  private static void saveSubImage(File srcImageFile, File descDir, int width, int height, Rectangle rect)
    throws IOException
  {
    ImageHepler.cut(srcImageFile, descDir, width, height, rect);
  }
}