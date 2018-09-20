package com.tecnicaltest.repository;

import org.springframework.data.repository.CrudRepository;

import com.tecnicaltest.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    
    boolean existsByInvoiceNumber(Long number);
    
    Invoice findByInvoiceNumber(Long number);
	
}
