package axon.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ReadAndSend {
	
	public List<String> readFromExcel(){
		List<String> ret = new ArrayList<>();
		try(FileInputStream fis = new FileInputStream(new File("emails.xls"))){
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()){
				Row row = rows.next();
				if(row.getCell(0) != null){
					ret.add(row.getCell(0).getStringCellValue());
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return ret;		
	}
	
	public void sendMail(List<String> emails){
			Connection con = null;
			String empName = "";
			String emailId = "";
			String locationName = "";
			for(String email:emails){
				if(email.trim() != ""){
	               final String username = "testsynergisticit@gmail.com";
	               final String password = "qwer12345";

	               Properties props = new Properties();
	               props.put("mail.smtp.auth", "true");
	               props.put("mail.smtp.starttls.enable", "true");
	               props.put("mail.smtp.host", "smtp.gmail.com");
	               props.put("mail.smtp.port", "587");

	               Session session = Session.getInstance(props,
	                 new javax.mail.Authenticator() {
	                       protected PasswordAuthentication getPasswordAuthentication() {
	                               return new PasswordAuthentication(username, password);
	                       }
	                 });

	               try {

	                       Message message = new MimeMessage(session);
	                       System.out.println(email);
	                       message.setFrom(new InternetAddress(username));
	                       message.setRecipients(Message.RecipientType.TO,
	                               InternetAddress.parse(email));
	                       message.setSubject("Interesting Offer For You");
	                       //message.setText();
	                       /*message.setContent("<!DOCTYPE html>"
		                       		+ "<html>"
		                       		+ "<body>"
		                       		+ "Are you interested in a bank loan? <br/>"
		                               + "<a href=\"http://localhost:8080/crm-bank/uploadApplicant.jsp">Click here!</a>"
		                               + "</body></html>", "text/html");*/
	                       
	                    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/webapp/uploadApplicant.html")))){
	                    	   String read;
	                    	   String all = "";
	                    	   while((read = reader.readLine()) != null){
	                    		   all += read;
	                    	   }
	                    	   message.setContent(all,"text/html");
	                       } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	                       Transport.send(message);

	                       System.out.println("Done");

	               } catch (MessagingException e) {
	                       throw new RuntimeException(e);
	               }
			}
		}
	}
	
	
	public static void main(String[] args) {
		ReadAndSend ras = new ReadAndSend();
		System.out.println(ras.readFromExcel());
		ras.sendMail(ras.readFromExcel());
		
	}

}
