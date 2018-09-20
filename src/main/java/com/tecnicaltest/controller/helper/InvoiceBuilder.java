package com.tecnicaltest.controller.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tecnicaltest.dto.business.ApiInvoice;
import com.tecnicaltest.model.Invoice;
import com.tecnicaltest.repository.CustomerRepository;

@Component
public class InvoiceBuilder {

    @Autowired
    CustomerBuilder customerBuilder;

    @Autowired
    CustomerRepository customerRepository;

    public InvoiceBuilder() {
        super();
    }

    public Invoice createInvoice(ApiInvoice invoice) {
        if (invoice == null) {
            return null;
        } else {
            return new Invoice(customerRepository.findOne(invoice.getCustomerId()), invoice.getId(), invoice.getNumber(), invoice.getDate(),
                    invoice.getAddress(), invoice.getProduct(), invoice.getQuantity(), invoice.getTotal(), invoice.getTaxes());
        }
    }

    public ApiInvoice createApiInvoice(Invoice invoice) {
        if (invoice == null) {
            return null;
        } else {
            Long customerId = invoice.getCustomer() == null ? null : invoice.getCustomer().getId();
            return new ApiInvoice(invoice.getId(), customerId, invoice.getDate(), invoice.getInvoiceNumber(), invoice.getDeliverAddress(),
                    invoice.getProductDescription(), invoice.getProductQuantity(), invoice.getTotalAmount(), invoice.getTaxes(), invoice.getGrandTotal());

        }
    }
}
