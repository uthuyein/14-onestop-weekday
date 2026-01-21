package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.api.outputs.SelectCategory;
import com.jdc.mkt.model.entities.Category;
import com.jdc.mkt.model.repositories.CategoryRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<SelectCategory> findAll() {
		var list = repo.findAll().stream().map(cat -> SelectCategory.from(cat)).toList();
		System.out.println(list);
		return list;
	}

	public List<SelectCategory> findByName(String name) {
		return repo.findByNameLikeIgnoreCaseAndIsActiveTrue(name.concat("%")).stream()
				.map(cat -> SelectCategory.from(cat)).toList();
	}

	public SelectCategory findById(int id) {
		return repo.findById(id).map(c -> SelectCategory.from(c)).orElseThrow(() -> new EntityNotFoundException());
	}

	@Transactional
	public ModificationResult<Integer> update(Integer id, CategoryForm form) {
		var category = id != null ? repo.findById(id).orElse(null) : null;
		category = repo.save(form.entity(category));
		return ModificationResult.status(category.getId(), ModifiedType.Update, category.getName());
	}

	@Transactional
	public ModificationResult<Integer> save(CategoryForm form) {
		var category = new Category();
		category.setActive(true);
		category = repo.save(form.entity(category));
		return ModificationResult.status(category.getId(), ModifiedType.Save, category.getName());
	}

}
