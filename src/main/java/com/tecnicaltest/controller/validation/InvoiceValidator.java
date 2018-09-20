package com.tecnicaltest.controller.validation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tecnicaltest.dto.business.ApiInvoice;
import com.tecnicaltest.repository.CustomerRepository;

@Component
public class InvoiceValidator {
    
    @Autowired
    CustomerRepository customerRepository;
    
    
    public ValidationResult validateInvoice(ApiInvoice invoice) {
        boolean valid = true;
        List<String> errors = new ArrayList<>();
        
        if (invoice == null) {
            valid = false;
            errors.add("invoice is null.");
        }
        if (invoice.getNumber() == null || invoice.getNumber() <= 0) {
            valid = false;
            errors.add("Invoice Number must be greater than 0.");
        }
        if (invoice.getDate() == null ) {
            valid = false;
            errors.add("Invoice date is not valid.");
        }
        if (invoice.getCustomerId() == null || invoice.getNumber() <= 0) {
            valid = false;
            errors.add("Customer Id must be greater than 0.");
        }
        if (!customerRepository.exists(invoice.getCustomerId())) {
            valid = false;
            errors.add("Customer with Id=" + invoice.getCustomerId() + " not found.");
        }
        if (invoice.getProduct() == null  || invoice.getProduct().isEmpty()) {
            valid = false;
            errors.add("Product Description is Empty.");
        }
        if (invoice.getQuantity() == null || invoice.getQuantity() <= 0) {
            valid = false;
            errors.add("Product Quantity must be greater than 0.");
        }
        if (invoice.getTotal() == null || invoice.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
            valid = false;
            errors.add("Product Total Cost must be greater than 0.");
        }
        
        return new ValidationResult(valid, errors);
    }
}
