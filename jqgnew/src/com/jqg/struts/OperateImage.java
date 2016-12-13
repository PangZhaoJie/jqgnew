package com.jqg.struts;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class OperateImage
{
  private String srcpath;
  private String subpath;
  private int x;
  private int y;
  private int width;
  private int height;

  public int getX()
  {
    return this.x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return this.y;
  }
  public void setY(int y) {
    this.y = y;
  }
  public int getWidth() {
    return this.width;
  }
  public void setWidth(int width) {
    this.width = width;
  }
  public int getHeight() {
    return this.height;
  }
  public void setHeight(int height) {
    this.height = height;
  }

  public String getSrcpath()
  {
    return this.srcpath;
  }
  public void setSrcpath(String srcpath) {
    this.srcpath = srcpath;
  }
  public String getSubpath() {
    return this.subpath;
  }
  public void setSubpath(String subpath) {
    this.subpath = subpath;
  }

  public void cut() throws IOException {
    FileInputStream is = null;
    ImageInputStream iis = null;
    try
    {
      is = new FileInputStream(this.srcpath);

      Iterator it = ImageIO.getImageReadersByFormatName("jpg");
      ImageReader reader = (ImageReader)it.next();

      iis = ImageIO.createImageInputStream(is);

      reader.setInput(iis, true);

      ImageReadParam param = reader.getDefaultReadParam();

      Rectangle rect = new Rectangle(this.x, this.y, this.width, this.height);

      param.setSourceRegion(rect);

      BufferedImage bi = reader.read(0, param);

      ImageIO.write(bi, "jpg", new File(this.subpath));
    } finally {
      if (is != null)
        is.close();
      if (iis != null)
        iis.close();
    }
  }
}