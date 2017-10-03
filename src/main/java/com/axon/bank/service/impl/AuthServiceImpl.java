package com.axon.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.axon.bank.dao.BankDao;
import com.axon.bank.service.AuthService;

@Service("AuthServiceImpl")
@Transactional(propagation=Propagation.REQUIRED)
public class AuthServiceImpl implements AuthService{

	@Autowired
	@Qualifier("BankDaoImpl")
	private BankDao bankDao;
	
	@Override
	public String authUser(String username, String password) {
		
		return bankDao.authUser(username, password);
	}

}
