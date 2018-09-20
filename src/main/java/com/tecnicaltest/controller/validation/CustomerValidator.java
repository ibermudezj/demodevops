package com.tecnicaltest.controller.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tecnicaltest.dto.business.ApiCustomer;
import com.tecnicaltest.repository.CustomerRepository;

@Component
public class CustomerValidator {
    
    @Autowired
    CustomerRepository customerRepository;
    
    public ValidationResult validateCustomer(ApiCustomer customer) {
        boolean valid = true;
        List<String> errors = new ArrayList<>();
        if (customer == null) {
            valid = false;
            errors.add("Customer is null.");
        }
        if (customer.getIdDocument() == null  || customer.getIdDocument().isEmpty()) {
            valid = false;
            errors.add("Document Id is Empty.");
        }
        if (customerRepository.existsByIdDocument(customer.getIdDocument())) {
            valid = false;
            errors.add("A user with Id Document=" + customer.getIdDocument() + " already exists.");
        }
        
        if (customer.getFirstName()  == null || customer.getFirstName().isEmpty()) {
            valid = false;
            errors.add("First Name is Empty.");
        }
        if (customer.getLastName()  == null || customer.getLastName().isEmpty()) {
            valid = false;
            errors.add("First Name is Empty");
        }
        
        return new ValidationResult(valid, errors);
    }
    
    

}
