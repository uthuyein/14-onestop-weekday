package com.jdc.mkt.model.repositories;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepo<T,ID>  extends JpaRepository<T, ID>{

	<R>List<R> findBy(Function<CriteriaBuilder, CriteriaQuery<R>> queryFun);
}
