  
package emailReport;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.AfterSuite;


public class EmailReport {

	
	public static void emailreport() {

		final String username = "pvalaboju@vertafore.com";
		final String password = "Phani@1512";

		Properties props = new Properties();
		
//		props.put("mail.pop3.auth", "true");
//		props.put("mail.pop3.starttls.enable","true");
//		props.put("mail.pop3.host", "outlook.office365.com");
//		props.put("mail.pop3.socketFactory.port", "993");
//		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.pop3.port", "587");
		
		props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.office365.com");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.port", "587");
	   
	
		
	//	Session session = Session.getDefaultInstance(props, null);

  	Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Phaneendraphani20@gmail.com"));
		//	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("AkkiReddy@vertafo.com"));
			message.setSubject("Testing Subject.html");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Hi," + "\n" + "Please, Find the Automation test Report for SFS" + "\n"
					+ "\n" + "Note : This is an automatic generated mail by Automation Script" + "\n" + "\n" + "\n"
					+ "\n" + "Thank you," + "\n" + "Phanindraa");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			File file = new File("C:\\Users\\valaboph\\eclipse-workspace\\SFS\\test-output\\xtentReport.html");
			String fileName = "Test Report.html";
			DataSource source = new FileDataSource(file);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(fileName);
			
//			MimeBodyPart messageBodyPart3 = new MimeBodyPart();
//			File file2 = new File("");
//			String fileName2 = "Test ReportAPI.log";
//			DataSource source2 = new FileDataSource(file2);
//			messageBodyPart3.setDataHandler(new DataHandler(source2));
//			messageBodyPart3.setFileName(fileName2);
//			
//			Multipart multipart = new MimeMultipart();
//
//			multipart.addBodyPart(messageBodyPart1);
//			multipart.addBodyPart(messageBodyPart2);
//			multipart.addBodyPart(messageBodyPart3);
//
//			message.setContent(multipart);
//
//			message.setContent(multipart);
			System.out.println("Sending Email Report.......");
			Transport.send(message);
			System.out.println("Report Email Sent");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}


