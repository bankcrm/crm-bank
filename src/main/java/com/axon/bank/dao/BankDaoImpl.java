package com.axon.bank.dao;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.axon.bank.dao.entity.ApplicantEntity;

public class BankDaoImpl extends HibernateDaoSupport implements BankDao{

	@Override
	public void addLoanApplicant(ApplicantEntity a) {
		System.out.println("Not implimented");
		
	}

	@Override
	public List<ApplicantEntity> getLoanApplicants() {
		System.out.println("Not implimented");
		// TODO Auto-generated method stub
		return null;
	}

}
