package com.jdc.mkt;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.mkt.model.repositories.BaseRepoImpl;


@Configuration
@EnableJpaRepositories(basePackageClasses = BaseRepoImpl.class)
public class ProductJpaConfig {

}
