package com.tecnicaltest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tecnicaltest.controller.helper.CustomerBuilder;
import com.tecnicaltest.controller.helper.InvoiceBuilder;
import com.tecnicaltest.controller.validation.InvoiceValidator;
import com.tecnicaltest.controller.validation.ValidationResult;
import com.tecnicaltest.dto.business.ApiInvoice;
import com.tecnicaltest.dto.request.ApiRequest;
import com.tecnicaltest.dto.response.ApiResponse;
import com.tecnicaltest.dto.response.SimpleApiResponse;
import com.tecnicaltest.model.Invoice;
import com.tecnicaltest.repository.CustomerRepository;
import com.tecnicaltest.repository.InvoiceRepository;

@RestController
public class InvoiceController {

    @Autowired
    private CustomerBuilder customerBuilder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvoiceBuilder invoiceBuilder;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceValidator invoiceValidator;
    
    @RequestMapping(value = "/technicaltest/api/invoice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<ApiInvoice>> getInvoiceById(@PathVariable("id") String id) {
        try {
            String message;
            HttpStatus status;
            ApiInvoice payload = invoiceBuilder.createApiInvoice(invoiceRepository.findOne(Long.valueOf(id)));

            if (payload == null) {
                // factura no encontrada
                message = "Not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                // factura encontrada
                message = "Success.";
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(new ApiResponse<>(true, message, payload), status);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/invoice/existsById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<Boolean>> existsById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, "", invoiceRepository.exists(Long.valueOf(id))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @RequestMapping(value = "/technicaltest/api/invoice/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<List<ApiInvoice>>> getAllInvoices() {
        try {
            String message = "Success.";
            HttpStatus status = HttpStatus.OK;
            List<ApiInvoice> apiInvoices = new ArrayList<>();
            for (Invoice i : invoiceRepository.findAll()) {
                apiInvoices.add(invoiceBuilder.createApiInvoice(i));
            }
            return new ResponseEntity<>(new ApiResponse<>(true, message, apiInvoices), status);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/technicaltest/api/invoice/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<Long>> getCountCustomers() {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, "Success.", invoiceRepository.count()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/invoice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SimpleApiResponse> createCustomer(@RequestBody ApiRequest<ApiInvoice> apiInvoice) {
        try {
            String message;
            boolean success;
            ValidationResult valid = invoiceValidator.validateInvoice(apiInvoice.getPayload());
            if (valid.getIsValid()) {
                Invoice invoice = invoiceBuilder.createInvoice(apiInvoice.getPayload());
                invoiceRepository.save(invoice);
                success = true;
                message = "Created invoice id=" + invoice.getId() + ", number=" + invoice.getInvoiceNumber() + ".";
            } else {
                success = false;
                message = "Validation Errors: " + valid.getValidationErrors().stream().reduce("", (s1, s2) -> s1 + "- " + s2 + ".\r\n") + ".'";
            }

            return new ResponseEntity<>(new SimpleApiResponse(success, message), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleApiResponse(false, "Can't create user. Internal Server Error: " + e.toString()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
