package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.api.outputs.SelectCategory;
import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.repositories.CategoryRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<SelectCategory> findAll() {
		return repo.findAll().stream().map(cat -> SelectCategory.from(cat)).toList();
	}

	public List<SelectCategory>  findByName(String name) {
		return repo.findByNameLikeIgnoreCaseAndIsActiveTrue(name.concat("%")).stream()
				.map(cat -> SelectCategory.from(cat)).toList();
	}

	public SelectCategory findById(int id) {
		return repo.findById(id).map(c -> SelectCategory.from(c)).orElseThrow(() -> new EntityNotFoundException());
	}

	@Transactional
	public Category save(CategoryForm form) {
		return repo.save(form.entity(new Category()));
	}

	@Transactional
	public Category update(Integer id, CategoryForm form) {
		var category = repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no entity from database!"));
		return repo.save(form.entity(category));
	}

}
