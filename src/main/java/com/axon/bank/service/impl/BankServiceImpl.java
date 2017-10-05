package com.axon.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.axon.bank.dao.BankDao;
import com.axon.bank.dao.entity.AgentCustomerEntity;
import com.axon.bank.dao.entity.ApplicantEntity;
import com.axon.bank.dao.entity.CustomerEntity;
import com.axon.bank.dao.entity.LoginEntity;
import com.axon.bank.form.ApplicantForm;
import com.axon.bank.form.CustomerForm;
import com.axon.bank.form.LoginForm;
import com.axon.bank.service.BankService;

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
			ace.setAgent(123);
			ace.setCustomerId(id);
			ace.setStatus((double)6/21);
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
		double setStatus = (double)status/(double)21;
		bankDao.setStatus(setStatus,customerEntity.getId());
		
	}
	
	private int checkFilled(Object o) {
		if(o == null){
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
	public String setAgentId(int id, String username) {
		bankDao.setAgentId(id, username);
		return "success";
	}

}
