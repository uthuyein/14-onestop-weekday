package com.jdc.mkt.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.search.SearchCustomerForm;
import com.jdc.mkt.api.outputs.SelectCustomer;
import com.jdc.mkt.model.services.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerApi {

	@Autowired
	private CustomerService service;
	
	List<SelectCustomer> findBy(@RequestBody SearchCustomerForm form){
		return service.findBy(form);
	}
}
