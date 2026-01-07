package com.jdc.mkt;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.mkt.model.repositories.BaseRepoImpl;


@Configuration
@EnableJpaRepositories(
		repositoryBaseClass = BaseRepoImpl.class)
public class ProductJpaConfig {

}
