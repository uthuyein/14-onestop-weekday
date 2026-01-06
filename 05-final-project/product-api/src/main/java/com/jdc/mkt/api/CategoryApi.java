package com.jdc.mkt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.CategoryForm;
import com.jdc.mkt.model.services.CategoryService;
import com.jdc.mkt.utils.StatusMessage;

@RestController
@RequestMapping("categories")
public class CategoryApi {

	@Autowired
	private CategoryService service;
	
	@PostMapping("/save")
	StatusMessage<Integer> save(@Validated @RequestBody CategoryForm form){
		var category = service.save(form);
		return StatusMessage.success(category.getId(),"%s has successfully save.".formatted(category.getName()));
	}
}
