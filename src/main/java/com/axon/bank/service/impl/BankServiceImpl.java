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
		System.out.println("HITING THE DAO LAYER TO FETCH THE DATA FROM THE DATABASE");
			
		List<ApplicantEntity> applicantEntityList = bankDao.getLoanApplicants();
		List<ApplicantForm> applicantFormList = new ArrayList<ApplicantForm>();
		for(ApplicantEntity applicantEntity: applicantEntityList ){
			ApplicantForm applicantForm = new ApplicantForm();
			BeanUtils.copyProperties(applicantEntity, applicantForm);
			applicantFormList.add(applicantForm);
		}
		return applicantFormList;
	}

}
