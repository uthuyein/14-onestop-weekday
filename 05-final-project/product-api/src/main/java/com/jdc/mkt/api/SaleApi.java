package com.jdc.mkt.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.mkt.api.inputs.SaleDetailForm;
import com.jdc.mkt.api.inputs.SaleForm;
import com.jdc.mkt.api.inputs.search.SearchSaleForm;
import com.jdc.mkt.api.outputs.SelectSaleDetail;
import com.jdc.mkt.model.services.SaleService;
import com.jdc.mkt.utils.ModificationResult;

/**
 * SaleApi
 *
 * @author MKT
 * @created Jan 12, 2026
 * @project product-api
 */

@RestController
@RequestMapping("sales")
public class SaleApi {
	
	@Autowired
	private SaleService service;
	
	@GetMapping
	List<SelectSaleDetail> findBy(@RequestBody SearchSaleForm form){
		return service.findBy(form);
	}
	
	@GetMapping("/add")
	void add(@RequestBody SaleDetailForm form){
		 service.addSaleDetail(form);
	}
	
	
	@PostMapping
	ModificationResult<UUID> update(@RequestParam(required = false) UUID id,@Validated @RequestBody SaleForm form){
		return service.update(id,form);
	}
	
}
