package com.axon.bank.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.axon.bank.dao.BankDao;
import com.axon.bank.dao.entity.LoginEntity;

@Service("BankUserSpringSecurityServiceImpl")
@Scope("singleton")
public class BankUserSpringSecurityServiceImpl implements UserDetailsService{

	@Autowired
	@Qualifier("BankDaoImpl")
	private BankDao bankDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Declare a null Spring user
		UserDetails user = null;
		LoginEntity loginEntity = bankDao.findRoleByUsername(username);
		if(loginEntity == null){
			UsernameNotFoundException ex = new UsernameNotFoundException("Sorry, username doesn't exist");
			throw ex;
		}else{
			user = new User(username, loginEntity.getPassword(), true, true, true, true,getAuthorities(loginEntity.getRole()));
		}
		return user;
	}
	
	public Collection<GrantedAuthority> getAuthorities(String role){
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		
		authList.add(new GrantedAuthorityImpl(role));
		return authList;
		
	}

}
