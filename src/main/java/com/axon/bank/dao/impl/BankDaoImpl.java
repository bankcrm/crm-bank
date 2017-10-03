package com.axon.bank.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.axon.bank.dao.BankDao;
import com.axon.bank.dao.entity.ApplicantEntity;
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
	
	
	@Override
	public void addLoanApplicant(ApplicantEntity applicantEntity) {
		super.getHibernateTemplate().save(applicantEntity);
		System.out.println("Added applicant");
		
	}

	@Override
	public List<ApplicantEntity> getLoanApplicants() {

		return (List<ApplicantEntity>) super.getHibernateTemplate().find("from ApplicantEntity");

	}
	
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

}
