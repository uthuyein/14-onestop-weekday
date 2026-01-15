package com.jdc.mkt.model.services;

import java.awt.Dialog.ModalityType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.mkt.api.inputs.SizeForm;
import com.jdc.mkt.model.entities.Size;
import com.jdc.mkt.model.repositories.SizeRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

@Service
public class SizeService {

	@Autowired
	private SizeRepo repo;
	
	public List<Size> findAll() {
		return repo.findAll();
	}

	public Size findById(Integer id) {
		return repo.findById(id).orElseThrow();
	}

	public ModificationResult<Integer> update(Integer id, SizeForm form) {
		var s = null == id ? null : (repo.findById(id).orElse(null));
		ModifiedType type = s == null ? ModifiedType.Save : ModifiedType.Update;
		
		if(type == ModifiedType.Save) {
			repo.save(form.entity(new Size()));
		}else {
			repo.save(s);
		}
		
		return ModificationResult.status(s.getId(), type, s.getName());
	}

}
