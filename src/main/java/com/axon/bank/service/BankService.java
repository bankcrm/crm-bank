package com.axon.bank.service;

import java.util.List;

import com.axon.bank.form.ApplicantForm;

public interface BankService {
	public void add(ApplicantForm a);
	public List<ApplicantForm> selectAllApplicants();
}
