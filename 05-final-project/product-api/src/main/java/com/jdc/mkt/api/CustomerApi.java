package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.CustomerForm;
import com.jdc.mkt.api.outputs.SelectCustomer;
import com.jdc.mkt.model.services.CustomerService;
import com.jdc.mkt.utils.ModificationResult;

@RestController
@RequestMapping("/member/customers")
public class CustomerApi {

	@Autowired
	private CustomerService service;
	
	@GetMapping
	List<SelectCustomer> findAll(){
		return service.findByIsActive();
	}
		
	@PostMapping
	ModificationResult<Integer>save( @RequestBody CustomerForm form ){
		return service.save(form);
	}
	
	@PutMapping("/{id}")
	ModificationResult<Integer> update(@PathVariable Integer id, @RequestBody CustomerForm form ){
		return service.update(id,form);
	}
	
	@PutMapping("/{id}/deactivate")
	public SelectCustomer deactivateCustomer(@PathVariable int id) {
	    return service.deactivate(id); 
	}
	
	@GetMapping("/find")
	List<SelectCustomer> findBy(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword){	
		return service.findBy(type,keyword);
	}
}
