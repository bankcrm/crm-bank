package com.axon.bank.dao;

import java.util.List;

import com.axon.bank.dao.entity.ApplicantEntity;
import com.axon.bank.dao.entity.LoginEntity;

public interface BankDao {
	public void addLoanApplicant(ApplicantEntity a);
	public List<ApplicantEntity> getLoanApplicants();
	public String authUser(String username, String password);
	public LoginEntity findRoleByUsername(String username);

}
