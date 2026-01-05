package com.jdc.mkt.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;

public class BaseRepoImpl<T,ID> extends SimpleJpaRepository<T, ID> implements JpaRepository<T, ID>{

	public BaseRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
	}

}
