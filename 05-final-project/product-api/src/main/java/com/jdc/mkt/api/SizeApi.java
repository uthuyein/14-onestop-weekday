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

import com.jdc.mkt.api.inputs.SizeForm;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.services.SizeService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("/sizes")
public class SizeApi {

	@Autowired
	private SizeService service;
	
	@GetMapping
	List<Size> findAll(){
		return service.findAll();
	}
	
	@GetMapping("{id}")
	Size findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer>update(@RequestParam(required = false) Integer id,@Validated @RequestBody SizeForm size){
		return service.update(id,size);
	}
}
