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
	List<SelectCategory> findAll(@RequestParam(required = false) String name){
		if(null != name) {
			return service.findByName(name);
		}
		return service.findAll();
	}
	
	@GetMapping("{id}")
	SelectCategory findById(@PathVariable int id) {
		return service.findById(id);
	}
	

	@PostMapping
	ModificationResult<Integer> update(@RequestParam(required = false) Integer id, @Validated @RequestBody CategoryForm form){
		return service.update(id,form);
	}
}
