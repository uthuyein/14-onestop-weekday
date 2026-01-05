package com.jdc.mkt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.api.outputs.CategoryInfo;
import com.jdc.mkt.model.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryApi {

	@Autowired
	private CategoryService service;
	
	@PostMapping("/save")
	CategoryInfo save(@Validated @RequestBody CategoryForm form){
		var category = service.save(form);
		var info = CategoryInfo.from(category);		
		return info;
	}
}
