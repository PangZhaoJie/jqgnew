package com.jqg.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		Mail authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			authenticator = new Mail(mailInfo.getUserName(),
					mailInfo.getPassword());
		}

		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);

			Address from = new InternetAddress(mailInfo.getFromAddress());

			mailMessage.setFrom(from);

			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);

			mailMessage.setSubject(mailInfo.getSubject());

			mailMessage.setSentDate(new Date());

			String mailContent = mailInfo.getContent();
//			mailMessage.setText(mailContent);
			mailMessage.setContent(mailContent, "text/html; charset=utf-8");  
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
}
