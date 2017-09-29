package com.axon.bank.dao;

import java.util.List;

import com.axon.bank.dao.entity.ApplicantEntity;

public interface BankDao {
	public void addLoanApplicant(ApplicantEntity a);
	public List<ApplicantEntity> getLoanApplicants();

}
