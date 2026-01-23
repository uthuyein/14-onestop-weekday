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
	
	public List<SelectCustomer> findByIsActive(){
		return repo.findAll().stream().filter(c -> c.isActive())
				.map(SelectCustomer :: from).toList();
	}

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
		
		customer = repo.save(form.entity(customer));
		return ModificationResult.status(customer.getId(), ModifiedType.Update, customer.getName());
	}

	@Transactional
	public ModificationResult<Integer> save(CustomerForm form) {
		var customer = new Customer();
		customer.setActive(true);
		customer = repo.save(form.entity(customer));
		return ModificationResult.status(customer.getId(), ModifiedType.Save, customer.getName());
	}
}
