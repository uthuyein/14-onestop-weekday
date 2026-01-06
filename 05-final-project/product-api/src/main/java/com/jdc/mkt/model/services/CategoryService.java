package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.api.outputs.CategoryInfo;
import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.repositories.CategoryRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public Category save(CategoryForm form) {	
		return repo.save(form.entity(null));
	}

	public Category update(Integer id, CategoryForm form) {
		return repo.save(form.entity(id));
	}

	public List<CategoryInfo> findAll() {
		return repo.findAll().stream().map(cat -> CategoryInfo.from(cat)).toList();
	}

	public CategoryInfo findById(int id) {
			return repo.findById(id).map(c -> CategoryInfo.from(c)).orElseThrow(() -> new EntityNotFoundException());
	}

	public CategoryInfo findByName(String name) {
		return repo.findByNameAndIsActiveTrue(name).map(c -> CategoryInfo.from(c)).orElseThrow(() -> new EntityNotFoundException());
	}
}
