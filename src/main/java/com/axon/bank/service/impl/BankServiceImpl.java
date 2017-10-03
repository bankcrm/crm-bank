package com.axon.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.axon.bank.dao.BankDao;
import com.axon.bank.dao.entity.ApplicantEntity;
import com.axon.bank.form.ApplicantForm;
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

}
