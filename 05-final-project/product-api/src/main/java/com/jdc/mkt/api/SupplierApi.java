package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.SupplierForm;
import com.jdc.mkt.api.outputs.SelectSupplier;
import com.jdc.mkt.model.services.SupplierService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("suppliers")
@Transactional(readOnly = true)
public class SupplierApi {

	@Autowired
	private SupplierService service;
	
	@GetMapping
	List<SelectSupplier> getAll(@RequestParam(required = false) String name){
		if(StringUtils.hasLength(name)) {
			return service.findByName(name);
		}
		return service.findAll();
	}
	
	@GetMapping("{id}")
	SelectSupplier findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer> update(@RequestParam(required = false) Integer id, @RequestBody SupplierForm form){
		return service.update(id,form);
	}
}
