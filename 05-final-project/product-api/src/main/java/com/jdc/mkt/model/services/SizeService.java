package com.jdc.mkt.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.SizeForm;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.repositories.SizeRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

@Service
@Transactional(readOnly = true)
public class SizeService {

	@Autowired
	private SizeRepo repo;
	
	public List<Size> findAll() {
		return repo.findAll();
	}

	public Size findById(Integer id) {
		return repo.findById(id).orElseThrow();
	}

	@Transactional
	public ModificationResult<Integer> update(Integer id, SizeForm form) {
		var size = null == id ? null : (repo.findById(id).orElse(null));
		
		size = repo.save(form.entity(size));
		return ModificationResult.status(size.getId(), ModifiedType.Update, size.getName());
	}

	@Transactional
	public ModificationResult<Integer> save(SizeForm form) {
		var size = new Size();
		size.setActive(true);
		size = repo.save(form.entity(size));
		return ModificationResult.status(size.getId(), ModifiedType.Save, size.getName());
	}

}
