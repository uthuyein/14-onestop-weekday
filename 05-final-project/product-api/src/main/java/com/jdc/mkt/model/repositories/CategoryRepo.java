package com.jdc.mkt.model.repositories;

import java.util.List;

import com.jdc.mkt.model.entities.Category;

public interface CategoryRepo extends BaseRepo<Category, Integer> {
	List<Category> findByNameLikeIgnoreCaseAndIsActiveTrue(String name);

}
