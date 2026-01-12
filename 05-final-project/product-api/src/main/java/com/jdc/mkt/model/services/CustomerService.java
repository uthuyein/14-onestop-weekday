package com.jdc.mkt.model.services;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.api.inputs.CustomerForm;
import com.jdc.mkt.api.inputs.search.SearchCustomerForm;
import com.jdc.mkt.api.outputs.SelectCustomer;
import com.jdc.mkt.model.entities.Customer;
import com.jdc.mkt.model.entities.Customer_;
import com.jdc.mkt.model.repositories.CustomerRepo;
import com.jdc.mkt.utils.ModificationResult;
import com.jdc.mkt.utils.ModificationResult.ModifiedType;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo repo;

	public List<SelectCustomer> findBy(SearchCustomerForm form) {
		return repo.findBy(SearchFunction(form));
	}

	private Function<CriteriaBuilder, CriteriaQuery<SelectCustomer>> SearchFunction(SearchCustomerForm form) {
		return cb -> {
			var cq = cb.createQuery(SelectCustomer.class);
			var root = cq.from(Customer.class);
			var contact = root.join(Customer_.contact);
			var address = root.join(Customer_.address);

			SelectCustomer.select(cb, cq, root, contact, address);
			
			if (null != form) {
				cq.where(form.where(cb, root, contact, address));
			}
			return cq;
		};
	}
	
	@Transactional
	public ModificationResult<Integer>  update(Integer id, CustomerForm form) {
		var customer = id != null ? repo.findById(id).orElse(null) : null;
		
		ModifiedType update = customer == null ? ModifiedType.Save : ModifiedType.Update;		
		customer = repo.save(update == ModifiedType.Update ? form.entity(customer):form.entity( new Customer()));
			
		return ModificationResult.success(customer.getId(),update,customer.getName());
	}
}
