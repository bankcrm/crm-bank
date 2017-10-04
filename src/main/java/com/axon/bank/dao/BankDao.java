package com.axon.bank.dao;

import java.util.List;

import com.axon.bank.dao.entity.AgentCustomerEntity;
import com.axon.bank.dao.entity.ApplicantEntity;
import com.axon.bank.dao.entity.CustomerEntity;
import com.axon.bank.dao.entity.LoginEntity;

public interface BankDao {
	public void addLoanApplicant(ApplicantEntity a);
	public List<ApplicantEntity> getLoanApplicants();
	public String authUser(String username, String password);
	public LoginEntity findRoleByUsername(String username);
	public String changeStatus(int id, String status);
	public List<ApplicantEntity> getPendingApplicants();
	public ApplicantEntity getLoanApplicant(int id);
	public void makeCustomer(CustomerEntity customer);
	public void addAgentCustomerRelation(AgentCustomerEntity ace);
	public List<CustomerEntity> getAgentsCustomers(String name);
	public void updateCustomer(CustomerEntity customerEntity);
	public List<LoginEntity> getConnectedAgent();
	public List<CustomerEntity> getAcceptedApplicants();

}
