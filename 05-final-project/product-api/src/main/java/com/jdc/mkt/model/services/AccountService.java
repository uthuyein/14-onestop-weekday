/**
 * 
 */
package com.jdc.mkt.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.SignUpForm;
import com.jdc.mkt.model.entities.Account;
import com.jdc.mkt.model.repositories.AccountRepo;
import com.jdc.mkt.utils.BusinessException;

/**
 * AccountService
 *
 * @author MKT
 * @created Jan 13, 2026
 * @project product-api
 */

@Service
public class AccountService {

	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * @param name
	 * @return
	 */
	public Account findByEmail(String name) {
		return repo.findOneByEmail(name).orElse(null);
	}

	/**
	 * @param form
	 * @return
	 */
	public Account create(SignUpForm form) {
		if(null != findByEmail(form.email())){
			throw new BusinessException("Email Aleready Used !");
		}
		return repo.save(form.entity(passwordEncoder));
	}
}
