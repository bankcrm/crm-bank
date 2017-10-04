package com.axon.bank.service;

import java.util.List;

import com.axon.bank.form.ApplicantForm;
import com.axon.bank.form.CustomerForm;

public interface BankService {
	public void add(ApplicantForm a);
	public List<ApplicantForm> selectAllApplicants();
	public void changeStatus(int id, String status);
	public List<ApplicantForm> selectPendingApplicants();
	public List<CustomerForm> selectAgentsCustomers(String name);
	public void updateCustomer(CustomerForm customer);
}
