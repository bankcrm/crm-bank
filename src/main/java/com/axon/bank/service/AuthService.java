package com.axon.bank.service;

public interface AuthService {

	public String authUser(String username, String password);
	public String setLogoutStatus(String username);
}
