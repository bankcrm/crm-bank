package com.axon.bank.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.axon.bank.dao.BankDao;
import com.axon.bank.dao.entity.AgentCustomerEntity;
import com.axon.bank.dao.entity.ApplicantEntity;
import com.axon.bank.dao.entity.CustomerEntity;
import com.axon.bank.dao.entity.LoginEntity;

@Repository("BankDaoImpl")
@Scope("singleton")
@Transactional
public class BankDaoImpl extends HibernateDaoSupport implements BankDao{

	@Autowired
	@Qualifier("bankSessionFactory")
	public void setCustomerSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * This method is upload applicant information
	 */
	@Override
	public void addLoanApplicant(ApplicantEntity applicantEntity) {
		super.getHibernateTemplate().save(applicantEntity);
		System.out.println("Added applicant");
		
	}

	/**
	 * This method is to get the list of all applicants
	 */
	@Override
	public List<ApplicantEntity> getLoanApplicants() {
		return (List<ApplicantEntity>) super.getHibernateTemplate().find("from ApplicantEntity");
	}
	
	/**
	 * This method is to get the list of all connected agents at the team leader page
	 */
	@Override
	public List<LoginEntity> getConnectedAgent(){
		List<LoginEntity> agentList = (List<LoginEntity>)super.getHibernateTemplate().find("from LoginEntity where role='agent' and status = 1");
	    return agentList;
	}
	/**
	 * This method is to get the accepted request applicants at the team leader page
	 * @return applicantList
	 */
	@Override
	public List<CustomerEntity> getAcceptedApplicants(){
		List<AgentCustomerEntity> agentCustomerList = getAgentCustomerList();
		System.out.println(agentCustomerList);
		List<CustomerEntity> applicantList = new ArrayList<CustomerEntity>();
		for(AgentCustomerEntity entity : agentCustomerList){
			System.out.println(entity.getAgent());
			List<CustomerEntity> customer = (List<CustomerEntity>)super.getHibernateTemplate().find("from CustomerEntity where id = " + entity.getCustomerId());
			applicantList.add(customer.get(0));
			
		}
		
		return applicantList;
	}
	
	public List<AgentCustomerEntity> getAgentCustomerList(){
		List<AgentCustomerEntity> agentCustomerList = (List<AgentCustomerEntity>) super.getHibernateTemplate().find("from AgentCustomerEntity where agent = 1");
		return agentCustomerList;
	}
	/**
	 * This method is for spring security
	 */
	@Override
	public String authUser(String username, String password){
		String role = "";
		List<LoginEntity>  list = (List<LoginEntity>) super.getHibernateTemplate().find("from loginEntity where username=? and password=? ", username, password);
		if(list.isEmpty()){
			role="";
		}else{
			role=list.get(0).getRole();
		}
		return role;
	}
	
	/**
	 * This method is also a utility method for spring security
	 */
	@Override
	public LoginEntity findRoleByUsername(String username){
		LoginEntity loginEntity = null;
		
		List<LoginEntity> list = (List<LoginEntity>) super.getHibernateTemplate().find("from LoginEntity where username=?", username);
		if(list.isEmpty()){
			loginEntity = null;
		}else{
			loginEntity = list.get(0);
		}
		return loginEntity;
		
		
	}


	@Override
	public String changeStatus(int id, String status) {
		List<ApplicantEntity> apps = (List<ApplicantEntity>) super.getHibernateTemplate().find("from ApplicantEntity where id=?", id);
		ApplicantEntity applicant = apps.get(0);
		applicant.setStatus(status);
		super.getHibernateTemplate().save(applicant);
		return "success";
	}


	@Override
	public List<ApplicantEntity> getPendingApplicants() {
		return (List<ApplicantEntity>) super.getHibernateTemplate().find("from ApplicantEntity where status=?","pending");
	}


	@Override
	public ApplicantEntity getLoanApplicant(int id) {
		List<ApplicantEntity> apps = (List<ApplicantEntity>) super.getHibernateTemplate().find("from ApplicantEntity where id=?", id);
		return apps.get(0);
	}


	@Override
	public void makeCustomer(CustomerEntity customer) {
		super.getHibernateTemplate().save(customer);
		System.out.println("Added customer");	
	}


	@Override
	public void addAgentCustomerRelation(AgentCustomerEntity ace) {
		super.getHibernateTemplate().save(ace);
		System.out.println("Added relation");	
	}
	
	


	@Override
	public List<CustomerEntity> getAgentsCustomers(String name) {
		List<LoginEntity> login = (List<LoginEntity>) super.getHibernateTemplate().find("from LoginEntity where username = ?", name);
		List<AgentCustomerEntity> relations = (List<AgentCustomerEntity>) super.getHibernateTemplate().find("from AgentCustomerEntity where agent = ?", login.get(0).getLid());
		List<CustomerEntity> customers = new ArrayList<>();
		for(AgentCustomerEntity relation : relations){
		 customers.addAll((List<CustomerEntity>) super.getHibernateTemplate().find("from CustomerEntity where id=?",relation.getCustomerId()));
		}
		 return customers;
	}


	@Override
	public void updateCustomer(CustomerEntity customerEntity) {
		System.out.println("Merging: " + customerEntity);
		super.getHibernateTemplate().merge(customerEntity);
	}

	@Override
	public void setStatus(double setStatus, int cid) {
		List<AgentCustomerEntity> relations = (List<AgentCustomerEntity>) super.getHibernateTemplate().find("from AgentCustomerEntity where customerId = ?", cid);
		AgentCustomerEntity relation = relations.get(0);
		relation.setStatus(setStatus);
		System.out.println(relation);
		System.out.println(setStatus);
		super.getHibernateTemplate().merge(relation);
		
	}

}
