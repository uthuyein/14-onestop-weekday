package com.jdc.mkt.model.repositories;

import java.util.Optional;

import com.jdc.mkt.model.entities.Account;

public interface AccountRepo extends BaseRepo<Account, Integer>{

	Optional<Account> findOneByEmail(String email);
}
