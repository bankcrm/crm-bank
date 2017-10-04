package com.axon.bank.service;

import java.util.List;

import com.axon.bank.form.ApplicantForm;
import com.axon.bank.form.CustomerForm;
import com.axon.bank.form.LoginForm;

public interface BankService {
	public void add(ApplicantForm a);
	public List<ApplicantForm> selectAllApplicants();
	public void changeStatus(int id, String status);
	public List<ApplicantForm> selectPendingApplicants();
	public List<CustomerForm> selectAgentsCustomers(String name);
	public void updateCustomer(CustomerForm customer);
	public List<LoginForm> getConnectedAgent();
	public List<CustomerForm> getAcceptedApplicants();
}
