package com.tecnicaltest.repository;

import org.springframework.data.repository.CrudRepository;

import com.tecnicaltest.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
    public boolean existsByIdDocument(String idDocument);
	
}
