package com.jqg.util;

import com.jqg.pojo.Moneycount;
import com.jqg.pojo.Uservip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Test
{
  public static int upLoad(String str, List<Moneycount> moneycountList)
    throws IOException, RowsExceededException, WriteException
  {
    OutputStream os = new FileOutputStream(str);

    WritableWorkbook workbook = Workbook.createWorkbook(os);
    WritableSheet sheet = workbook.createSheet("TestCreateExcel", 0);

    Label l = null;
    Number n = null;
    DateTime d = null;

    WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
    WritableCellFormat headerFormat = new WritableCellFormat(headerFont);

    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

    WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
    WritableCellFormat detFormat = new WritableCellFormat(detFont);

    NumberFormat nf = new NumberFormat("0.00000");
    WritableCellFormat priceFormat = new WritableCellFormat(detFont, nf);

    DateFormat df = new DateFormat("yyyy-MM-dd");
    WritableCellFormat dateFormat = new WritableCellFormat(detFont, df);

    l = new Label(0, 0, "��Ա�˻���Ϣ", headerFormat);
    sheet.addCell(l);

    int column = 0;
    sheet.addCell(new Label(column++, 2, "ID", titleFormat));
    sheet.addCell(new Label(column++, 2, "�û���", titleFormat));
    sheet.addCell(new Label(column++, 2, "���ʲ�", titleFormat));
    sheet.addCell(new Label(column++, 2, "�������", titleFormat));
    sheet.addCell(new Label(column++, 2, "������", titleFormat));
    sheet.addCell(new Label(column++, 2, "���ձ�Ϣ���", titleFormat));
    sheet.addCell(new Label(column++, 2, "���ձ���", titleFormat));
    sheet.addCell(new Label(column++, 2, "������Ϣ", titleFormat));
    sheet.addCell(new Label(column++, 2, "������Ϣ", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼ�����������", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼƳ�ֵ������", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼ������ֽ�", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼƳ�ֵ���", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼ�Ͷ�꽱��", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼ��ƹ㽱��", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼƳ�ֵ����", titleFormat));
    sheet.addCell(new Label(column++, 2, "�ۼ���Ͷ����", titleFormat));
    sheet.addCell(new Label(column++, 2, "��׬��Ϣ", titleFormat));
    sheet.addCell(new Label(column++, 2, "������Ϣ", titleFormat));

    for (int i = 0; i < moneycountList.size(); i++) {
      Moneycount moneycount = (Moneycount)moneycountList.get(i);
      column = 0;

      sheet.addCell(new Number(column++, i + 3, moneycount.getMoneyCountId().intValue(), detFormat));
      sheet.addCell(new Label(column++, i + 3, moneycount.getUservip().getUserName(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getTotalMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAvailableMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getFrozenMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getCollectTotalMoney()+ moneycount.getCollectInterestTotalFee().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getCollectTotalMoney(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getCollectInterestTotalFee().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getPayInterestTotalFee().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuWithdrawalMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuRechargeFee().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuWidthdrawMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuRechargeMoney().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuBidReward().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuOfflineRechargeReward().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuPromoteReward().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getAccuContinueBidReward().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getNetEarnInterest().doubleValue(), detFormat));
      sheet.addCell(new Number(column++, i + 3, moneycount.getNetPayInterest().doubleValue(), detFormat));
    }

    column = 0;
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 20);

    workbook.write();
    os.flush();

    workbook.close();
    os.close();
    System.out.print("<h2>������   .xls�ļ�</div>");
    return 3;
  }

  public static void main(String[] args)
    throws RowsExceededException, WriteException, IOException
  {
    OutputStream os = new FileOutputStream("E://a.xls");

    WritableWorkbook workbook = Workbook.createWorkbook(os);
    WritableSheet sheet = workbook.createSheet("TestCreateExcel", 0);

    Label l = null;
    Number n = null;
    DateTime d = null;

    WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
    WritableCellFormat headerFormat = new WritableCellFormat(headerFont);

    WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
    WritableCellFormat titleFormat = new WritableCellFormat(titleFont);

    WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
    WritableCellFormat detFormat = new WritableCellFormat(detFont);

    NumberFormat nf = new NumberFormat("0.00000");
    WritableCellFormat priceFormat = new WritableCellFormat(detFont, nf);

    DateFormat df = new DateFormat("yyyy-MM-dd");
    WritableCellFormat dateFormat = new WritableCellFormat(detFont, df);

    l = new Label(0, 0, "���ڲ��Ե�Excel�ļ�", headerFormat);
    sheet.addCell(l);

    int column = 0;
    l = new Label(column++, 2, "����", titleFormat);
    sheet.addCell(l);
    l = new Label(column++, 2, "����", titleFormat);
    sheet.addCell(l);
    l = new Label(column++, 2, "����", titleFormat);
    sheet.addCell(l);
    l = new Label(column++, 2, "�۸�", titleFormat);
    sheet.addCell(l);

    int i = 0;
    column = 0;
    l = new Label(column++, i + 3, "����   " + i, detFormat);
    sheet.addCell(l);
    d = new DateTime(column++, i + 3, new Date(), dateFormat);
    sheet.addCell(d);
    l = new Label(column++, i + 3, "CNY", detFormat);
    sheet.addCell(l);
    n = new Number(column++, i + 3, 5.678D, priceFormat);
    sheet.addCell(n);

    i++;
    column = 0;
    l = new Label(column++, i + 3, "����   " + i, detFormat);
    sheet.addCell(l);
    d = new DateTime(column++, i + 3, new Date(), dateFormat);
    sheet.addCell(d);
    l = new Label(column++, i + 3, "SGD", detFormat);
    sheet.addCell(l);
    n = new Number(column++, i + 3, 98832.0D, priceFormat);
    sheet.addCell(n);

    column = 0;
    sheet.setColumnView(column++, 30);
    sheet.setColumnView(column++, 20);
    sheet.setColumnView(column++, 10);
    sheet.setColumnView(column++, 20);

    workbook.write();
    os.flush();

    workbook.close();
    os.close();
    System.out.print("<h2>������   .xls�ļ�</div>");
  }
}