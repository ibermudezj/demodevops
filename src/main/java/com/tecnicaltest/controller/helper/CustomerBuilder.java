package com.tecnicaltest.controller.helper;

import org.springframework.stereotype.Component;

import com.tecnicaltest.dto.business.ApiCustomer;
import com.tecnicaltest.model.Customer;

@Component
public class CustomerBuilder {

	public CustomerBuilder() {
		super();
	}

	public ApiCustomer createApiCustomer(Customer customer) {
		return customer == null ? null : new ApiCustomer(customer.getId(), customer.getFirstName(), customer.getLastName(),
				customer.getIdDocument(), customer.getAddress());
	}

	public Customer createCustomer(ApiCustomer apiCustomer) {
		return apiCustomer == null ? null : new Customer(apiCustomer.getId(), apiCustomer.getFirstName(), apiCustomer.getLastName(),
				apiCustomer.getIdDocument(), apiCustomer.getAddress());

	}
	
	public Customer updateCustomer(Customer customer, ApiCustomer apiCustomer) {
		if (customer != null && apiCustomer != null) {
			customer.setFirstName(apiCustomer.getFirstName());
			customer.setLastName(apiCustomer.getLastName());
			customer.setIdDocument(apiCustomer.getIdDocument());
			customer.setAddress(apiCustomer.getAddress());
		}
		return customer;
	}
}
