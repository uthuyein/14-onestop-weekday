package com.jdc.mkt.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.repositories.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	public Category save(CategoryForm form) {
		return repo.save(form.entity());
	}

	
}
