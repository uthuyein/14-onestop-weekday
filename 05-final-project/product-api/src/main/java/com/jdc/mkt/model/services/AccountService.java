package com.jdc.mkt.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.model.repositories.AccountRepo;

@Service
public class AccountService{

	@Autowired
	private AccountRepo repo;
	
	
}
