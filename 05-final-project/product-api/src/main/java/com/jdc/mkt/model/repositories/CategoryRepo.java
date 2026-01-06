package com.jdc.mkt.model.repositories;

import java.util.Optional;

import com.jdc.mkt.model.entities.Category;

public interface CategoryRepo extends BaseRepo<Category, Integer> {

	Optional<Category> findByNameAndIsActiveTrue(String name);

}
