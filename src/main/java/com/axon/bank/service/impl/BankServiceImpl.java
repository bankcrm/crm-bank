package com.axon.bank.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.axon.bank.dao.BankDao;
import com.axon.bank.dao.entity.AgentCustomerEntity;
import com.axon.bank.dao.entity.ApplicantEntity;
import com.axon.bank.dao.entity.CustomerEntity;
import com.axon.bank.dao.entity.LoginEntity;
import com.axon.bank.form.ApplicantForm;
import com.axon.bank.form.CustomerForm;
import com.axon.bank.form.FileForm;
import com.axon.bank.form.LoginForm;
import com.axon.bank.service.BankService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

@Service("BankServiceImpl")
@Scope("singleton")
public class BankServiceImpl implements BankService {

	
	@Autowired
	@Qualifier("BankDaoImpl")
	private BankDao bankDao;
	
	@Override
	public void add(ApplicantForm applicantForm) {
		ApplicantEntity applicantEntity = new ApplicantEntity();
		BeanUtils.copyProperties(applicantForm, applicantEntity);
		bankDao.addLoanApplicant(applicantEntity);
		System.out.println("Adding ....");
		
	}

	@Override
	public List<ApplicantForm> selectAllApplicants() {

		List<ApplicantEntity> applicantEntities = bankDao.getLoanApplicants();
		List<ApplicantForm> applicantForms = new ArrayList<ApplicantForm>();
		for(ApplicantEntity entity : applicantEntities){
			ApplicantForm form = new ApplicantForm();
			BeanUtils.copyProperties(entity, form);
			applicantForms.add(form);
		}
		return applicantForms;

	}

	//Creating customer
	@Override
	public void changeStatus(int id, String status) {
		bankDao.changeStatus(id, status);
		if(status.equals("accepted")){
			ApplicantEntity applicant = bankDao.getLoanApplicant(id);
			CustomerEntity customer = new CustomerEntity();
			BeanUtils.copyProperties(applicant, customer);
			setUpCustomer(customer);
			bankDao.makeCustomer(customer);
			AgentCustomerEntity ace = new AgentCustomerEntity();
			ace.setAgent(1);
			ace.setCustomerId(id);
			ace.setStatus((double)6 * 100 /22);
			bankDao.addAgentCustomerRelation(ace);
		}
	}

	private void setUpCustomer(CustomerEntity customer) {
		customer.setAmountOfExperience(-1);
		customer.setEmiCount(-1);
		customer.setSalary(-1);
		customer.setTimeInArea(-1);		
	}

	@Override
	public List<ApplicantForm> selectPendingApplicants() {
		List<ApplicantEntity> applicantEntities = bankDao.getPendingApplicants();
		List<ApplicantForm> applicantForms = new ArrayList<ApplicantForm>();
		for(ApplicantEntity entity : applicantEntities){
			System.out.println("Entity: " + entity);
			ApplicantForm form = new ApplicantForm();
			BeanUtils.copyProperties(entity, form);
			applicantForms.add(form);
		}
		return applicantForms;
	}

	@Override
	public List<CustomerForm> selectAgentsCustomers(String name) {
		List<CustomerEntity> customerEntities = bankDao.getAgentsCustomers(name);
		List<CustomerForm> customerForms = new ArrayList<CustomerForm>();
		for(CustomerEntity entity : customerEntities){
			System.out.println("Entity: " + entity);
			CustomerForm form = new CustomerForm();
			BeanUtils.copyProperties(entity, form);
			customerForms.add(form);
		}
		return customerForms;
	}

	@Override
	public void updateCustomer(CustomerForm customerForm) {
		customerForm.setDob(customerForm.getDob());
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerForm, customerEntity);
		bankDao.updateCustomer(customerEntity);
		updateStatus(customerEntity);
	}
	
	private void updateStatus(CustomerEntity customerEntity) {
		int status = 0;
		status += checkFilled(customerEntity.getAddress());
		status += checkFilled(customerEntity.getAge());
		status += checkFilled(customerEntity.getAmount());
		status += checkFilled(customerEntity.getAmountOfExperience());
		status += checkFilled(customerEntity.getTimeInArea());
		status += checkFilled(customerEntity.getCity());
		status += checkFilled(customerEntity.getCompany());
		status += checkFilled(customerEntity.getCompanyJoinDate());
		status += checkFilled(customerEntity.getEmail());
		status += checkFilled(customerEntity.getDob());
		status += checkFilled(customerEntity.getGender());
		status += checkFilled(customerEntity.getEmiCount());
		status += checkFilled(customerEntity.getHomedetails());
		status += checkFilled(customerEntity.getLastmove());
		status += checkFilled(customerEntity.getLoanDetails());
		status += checkFilled(customerEntity.getSsn());
		status += checkFilled(customerEntity.getSalaryAccount());
		status += checkFilled(customerEntity.getSalary())*2;
		status += checkFilled(customerEntity.getName());
		status += checkFilled(customerEntity.getMobile());
		status += checkFilled(customerEntity.isDocument());
		double setStatus = (double) status/(double)22;
		bankDao.setStatus(setStatus*100,customerEntity.getId());
		
	}
	
	private int checkFilled(Object o) {
		if(o == null){
			return 0;
		} else {
			return 1;
		}
	}
	
	private int checkFilled(boolean b) {
		if(!b){
			return 0;
		} else {
			return 1;
		}
	}
	
	private int checkFilled(String o) {
		if(o == null || o.trim() == ""){
			return 0;
		} else {
			return 1;
		}
	}
	
	private int checkFilled(long l) {
		if(l == -1){
			return 0;
		} else {
			return 1;
		}
	}

	public List<LoginForm> getConnectedAgent() {
		List<LoginEntity> agentEntities = bankDao.getConnectedAgent();
		List<LoginForm> agentForms = new ArrayList<LoginForm>();
		for(LoginEntity entity: agentEntities){
			LoginForm loginForm = new LoginForm();
			BeanUtils.copyProperties(entity, loginForm);
			agentForms.add(loginForm);
		}
		return agentForms;
	}

	@Override
	public List<CustomerForm> getAcceptedApplicants() {
		List<CustomerEntity> applicantList = bankDao.getAcceptedApplicants();
		List<CustomerForm> formList = new ArrayList<CustomerForm>();
		for(CustomerEntity entity : applicantList){
			CustomerForm applicantForm = new CustomerForm();
			BeanUtils.copyProperties(entity, applicantForm);
			formList.add(applicantForm);
		}
		return formList;
	}

	@Override
	public String assignCustoemerToAgent(int id, String username) {
		bankDao.assignCustoemerToAgent(id, username);
		return "success";
	}

	
	@Override
	public List<Object> getProgessStatus() {
		return bankDao.getProgessStatus();
	}

	public String assignCustoemerToAgent() {
		return bankDao.assignCustoemerToAgent();
	}

	@Override
	public double isCompleted(int id) {
		return bankDao.isCompleted(id);
	}

	@Override
	public void setStatus(int id) {
		bankDao.setStatus(101, id);
	}

	@Override
	public void sendCompletedEmail(int id) {
		CustomerEntity customer = bankDao.getCustomer(id);
		Connection con = null;
		String empName = "";
		String emailId = "";
		String locationName = "";
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
                       System.out.println(customer.getEmail());
                       message.setFrom(new InternetAddress(username));
                       message.setRecipients(Message.RecipientType.TO,
                               InternetAddress.parse(customer.getEmail()));
                       message.setSubject("Loan Application Approved");
                       StringBuilder emailtext = new StringBuilder("<center><h1>Congratulations " + customer.getName() + "</h1></center><br/>");
                       emailtext.append("<center><h2>You have been approved for a $" + customer.getAmount() + ".00 loan!</h2></center><br/><br/>");
                       emailtext.append("<center>To keep us up to date, here is the information that you provided to us. Please notify us of any incorrect or changed information.</center><br/>");
               		emailtext.append("<label>Age: </label>" + customer.getAge() + "<br/>");
            		emailtext.append("<label>Address: </label>" + customer.getAddress() + "<br/>");   
            		emailtext.append("<label>Mobile: </label>" + customer.getMobile() + "<br/>"); 
            		emailtext.append("<label>Amount: </label>" + customer.getAmount() + "<br/>"); 
            		emailtext.append("<label>Social Security Number: </label>" + customer.getSsn() + "<br/>");	
            		emailtext.append("<label>Gender: </label>" + customer.getGender() + "<br/>");	
            		emailtext.append("<label>City: </label>" + customer.getCity() + "<br/>");
            		emailtext.append("<label>Company: </label>" + customer.getCompany() + "<br/>");	
            		emailtext.append("<label>Salaried? </label>" + customer.isSalaried()+ "<br/>");
            		emailtext.append("<label>Salary: </label>" + customer.getSalary() + "<br/>");	
            		emailtext.append("<label>Salary Account: </label>" + customer.getSalaryAccount() + "<br/>");	
            		emailtext.append("<label>Date Company Was Joined: </label>" + customer.getCompanyJoinDate().toString().substring(0, 10) + "<br/>");	
            		emailtext.append("<label>Years of Working Experience: </label>" + customer.getAmountOfExperience() + "<br/>");	
            		emailtext.append("<label>How long you have been located in the area: </label>" + customer.getTimeInArea() + "<br/>");	
            		emailtext.append("<label>When you last moved: </label>" + customer.getLastmove().toString().substring(0, 10) + "<br/>");	
            		emailtext.append("<label>Home Details: </label>" + customer.getHomedetails() + "<br/>");	
            		emailtext.append("<label>Date Of Birth: </label>" + customer.getDob().toString().substring(0, 10) + "<br/>");	
            		emailtext.append("<label>Loan Details: </label>" + customer.getLoanDetails() + "<br/>");	
            		emailtext.append("<label>Amount of EMIs: </label>" + customer.getEmiCount() + "<br/>");	
            		emailtext.append("<label>Email: </label>" + customer.getEmail() + "<br/>");	
                       
                       message.setContent(emailtext.toString(),"text/html");

                       Transport.send(message);

                       System.out.println("Done");

               } catch (MessagingException e) {
                       throw new RuntimeException(e);
               }
		
	}

	@Override
	public void setDocument(int id) {
		CustomerEntity customer = bankDao.getCustomer(id);
		customer.setDocument(true);
		System.out.println(customer);
		bankDao.updateCustomer(customer);
		updateStatus(customer);
	}

	@Override
	public List<CustomerForm> getCompletedCustomers() {
		List<CustomerEntity> customers = bankDao.getCompletedCustomers();
		List<CustomerForm> formList = new ArrayList<CustomerForm>();
		for(CustomerEntity entity : customers){
			CustomerForm applicantForm = new CustomerForm();
			BeanUtils.copyProperties(entity, applicantForm);
			formList.add(applicantForm);
		}
		return formList;
	}

}
