package axon.email;



import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndSendMarketing {

	private static final String FILE_NAME = "email1.xlsx";
	
	public List<String> readFromExcel(){
		List<String> list = new ArrayList<String>();
		
		Workbook workbook;
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			workbook = new XSSFWorkbook(excelFile);
		
		Sheet dataTypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = dataTypeSheet.iterator();
		
		while(iterator.hasNext()){
			Row currentRow = iterator.next();
			if(currentRow.getCell(0) != null){
				list.add(currentRow.getCell(0).getStringCellValue());
			}
		}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	public void sendEmails(String host, String port, final String username, final String password,
							String toAddress, String subject, String htmlBody, Map<String, String>mapInlineImages) throws AddressException, MessagingException{
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.user", username);
		props.put("mail.password", password);
		
		Authenticator auth = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {
				System.out.println("@@@@@@@@@@@@@@@@");
				return new PasswordAuthentication(username, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		// Creates a new email message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username));
		InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		
		// create message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(htmlBody, "text/html");
		
		// create multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		// Adds inline image attachments
		if (mapInlineImages != null && mapInlineImages.size() > 0) {
			Set<String> setImageID = mapInlineImages.keySet();

			for (String contentId : setImageID) {
				MimeBodyPart imagePart = new MimeBodyPart();
				imagePart.setHeader("Content-ID", "<" + contentId + ">");
				imagePart.setDisposition(MimeBodyPart.INLINE);

				String imageFilePath = mapInlineImages.get(contentId);
				try {
					imagePart.attachFile(imageFilePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				multipart.addBodyPart(imagePart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);
		Transport.send(msg);
	}
	public static void main(String[] args) {
		
		ReadAndSendMarketing app = new ReadAndSendMarketing();
		List<String> list = app.readFromExcel();
		for(String email: list){
			System.out.println(email);
		}
		
		System.out.println("$$$$$$$$$#######_____sending email");
		// STMP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "testsynergisticit@gmail.com";
		String password = "qwer12345";
		
		//String mailTo = "testsynergisticitsession2@gmail.com";
		String subject = "New email with inLine image";
		StringBuffer body = new StringBuffer("<html>");
		body.append("<head>");
		//body.append("<link rel=\"stylesheet\" href= \"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"");
		//body.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>");
		//body.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
		
		body.append("<script>");
		body.append("alert(\"Hello World\")");
		body.append("</script>");
		body.append("</head>");
		body.append("<body>");
		
		//body.append("<h1 onclick=\"this.innerHTML='Ooops'\">Click here!!!!!!!1</h1><br>");
		//body.append("<p><button type=\"button\" onclick='alert('Hello world!')'>Click Here</button></p><br>");
		body.append("<form action=\"http://localhost:8080/crm-bank/bank/applyloan\" method=\"post\">");
		body.append("Name: <input name=\"name\" type=\"text\" required/><br/>");
		body.append("Age: <input name=\"age\" type=\"text\" required/><br/>");
		body.append("Address: <input name=\"address\" type=\"text\" required/><br/>");
		body.append("Contact Number: <input name=\"mobile\" type=\"text\" required/><br/>");
		body.append("Loan Amount: <input name=\"amount\" type=\"text\" required/><br/>");
		body.append("SSN: <input name=\"ssn\" type=\"text\" required/><br/>");
		body.append("<input type=\"submit\" />");
		body.append("</form>");
		
		//body.append("");
		
		body.append("<p><img src=\"cid:image1\" width=\"30%\" height=\"30%\" /></p><br>");
		body.append("<span>End of the message</span>");
		body.append("</body>");
		body.append("</html>");
		
		Map<String, String> inlineImages = new HashMap<String, String>();
		inlineImages.put("image1", "/Users/JianLi/Desktop/slides/bank.jpg");
		
		try{
			for(String mailTo : list){
				app.sendEmails(host,port,mailFrom,password,mailTo,subject, body.toString(),inlineImages);
				System.out.println("Done");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
