package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.api.outputs.SelectCategory;
import com.jdc.mkt.model.services.CategoryService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("categories")
public class CategoryApi {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	List<SelectCategory> index(){
		return service.findAll();
	}
	
	@GetMapping("{id}")
	SelectCategory findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@GetMapping("/findByName")
	SelectCategory findByName(@RequestParam(name = "category") String name) {
		return service.findByName(name);
	}
	
	@PostMapping("/save")
	ModificationResult<Integer> save(@Validated @RequestBody CategoryForm form){
		var category = service.save(form);
		return ModificationResult.success(category.getId(),"%s has successfully save.".formatted(category.getName()));
	}
	
	@PostMapping("/update/{id}")
	ModificationResult<Integer> update(@PathVariable(required = false) Integer id, @Validated @RequestBody CategoryForm form){
		var category = service.update(id,form);
		return ModificationResult.success(category.getId(),"%s has successfully update.".formatted(category.getName()));
	}
}
