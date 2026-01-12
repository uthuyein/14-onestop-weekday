package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.SupplierForm;
import com.jdc.mkt.api.outputs.SelectSupplier;
import com.jdc.mkt.model.entities.Supplier;
import com.jdc.mkt.model.repositories.SupplierRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

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
	public ModificationResult<Integer> update(Integer id,SupplierForm form) {
		var supplier = id != null ? repo.findById(id).orElse(null) : null;
		
		ModifiedType update = supplier == null ? ModifiedType.Save : ModifiedType.Update;		
		supplier = repo.save(update == ModifiedType.Update ? form.entity(supplier): form.entity(new Supplier()));
			
		return ModificationResult.success(supplier.getId(),update,supplier.getName());
	}

}
