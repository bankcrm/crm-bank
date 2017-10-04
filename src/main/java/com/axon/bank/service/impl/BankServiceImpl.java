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
			bankDao.makeCustomer(customer);
			AgentCustomerEntity ace = new AgentCustomerEntity();
			ace.setAgent(123);
			ace.setCustomerId(id);
			ace.setStatus(6/22);
			bankDao.addAgentCustomerRelation(ace);
		}
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

}
