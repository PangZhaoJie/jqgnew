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

    mailInfo.setSubject("�һ�����");

    String code = ""+System.currentTimeMillis();
    System.out.println("code" + code);
    String ss = "�������";
    StringBuffer sb = new StringBuffer("����������Ӽ����˺ţ�48Сʱ��Ч����������ע���˺ţ�����ֻ��ʹ��һ�Σ��뾡�켤�");
    sb.append("http://localhost:8080/jqg/user/activate?action=activate&email=");
    sb.append("2694026436@qq.com");
    sb.append("&validateCode=" + code);

    mailInfo.setContent(sb.toString());

    SendMail sms = new SendMail();

    boolean row = sms.sendTextMail(mailInfo);
    if (!row)
    {
      System.out.println("���䷢��ʧ��");
    }
    else System.out.println("�ɹ�");
  }
}