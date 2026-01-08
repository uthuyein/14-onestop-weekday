package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.SupplierForm;
import com.jdc.mkt.api.outputs.SelectSupplier;
import com.jdc.mkt.model.entities.Supplier;
import com.jdc.mkt.model.repositories.SupplierRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class SupplierService {
	
	@Autowired
	private SupplierRepo repo;

	public List<SelectSupplier> findAll() {
		return repo.findAll().stream().map(s -> SelectSupplier.from(s)).toList();
	}
	
	public List<SelectSupplier> findByName(String name) {
		return repo.findSupplierByNameLikeIgnoreCase(name.concat("%"))
				.stream().map(s -> SelectSupplier.from(s)).toList();
	}


	public SelectSupplier findById(Integer id) {
		return repo.findById(id)
				.map(s ->SelectSupplier.from(s))
				.orElseThrow(() -> new EntityNotFoundException("There is no entity from database !"));
	}

	
	@Transactional
	public SelectSupplier save(SupplierForm form) {
		var select = repo.save(form.entity(new Supplier()));
		return SelectSupplier.from(select) ;
	}
	
	@Transactional
	public SelectSupplier update(Integer id,SupplierForm form) {
		var sup = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no entity from database !"));
		var select = repo.save(form.entity(sup));
		return SelectSupplier.from(select) ;
	}

}
