package com.jqg.util;

import java.io.PrintStream;

public class MailTest
{
  public static void main(String[] args)
  {
    MailSenderInfo mailInfo = new MailSenderInfo();
    mailInfo.setMailServerHost("smtp.mxhichina.com");
    mailInfo.setMailServerPort("25");
    mailInfo.setValidate(true);

    mailInfo.setUserName("hn@nuochenjinrong.com");

    mailInfo.setPassword("ncjr147369");

    mailInfo.setFromAddress("hn@nuochenjinrong.com");

    mailInfo.setToAddress("2694026436@qq.com");

    mailInfo.setSubject("找回密码");

    String code = ""+System.currentTimeMillis();
    System.out.println("code" + code);
    String ss = "邮箱测试";
    StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！");
    sb.append("http://localhost:8080/jqg/user/activate?action=activate&email=");
    sb.append("2694026436@qq.com");
    sb.append("&validateCode=" + code);

    mailInfo.setContent(sb.toString());

    SendMail sms = new SendMail();

    boolean row = sms.sendTextMail(mailInfo);
    if (!row)
    {
      System.out.println("邮箱发送失败");
    }
    else System.out.println("成功");
  }
}