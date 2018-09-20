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
import com.tecnicaltest.controller.validation.CustomerValidator;
import com.tecnicaltest.controller.validation.ValidationResult;
import com.tecnicaltest.dto.business.ApiCustomer;
import com.tecnicaltest.dto.request.ApiRequest;
import com.tecnicaltest.dto.response.ApiResponse;
import com.tecnicaltest.dto.response.SimpleApiResponse;
import com.tecnicaltest.model.Customer;
import com.tecnicaltest.repository.CustomerRepository;

@RestController
public class CustomerController {

    @Autowired
    private CustomerBuilder customerBuilder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CustomerValidator customerValidator;

    public CustomerController() {
        super();
    }

    @RequestMapping(value = "/technicaltest/api/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<ApiCustomer>> getCustomerById(@PathVariable("id") String id) {
        try {
            String message;
            HttpStatus status;
            ApiCustomer payload = customerBuilder.createApiCustomer(customerRepository.findOne(Long.valueOf(id)));

            if (payload == null) {
                // usuario no encontrado
                message = "Not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                // usuario encontrado
                message = "Success.";
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(new ApiResponse<>(true, message, payload), status);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/customer/existsById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<Boolean>> existsById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, "", customerRepository.exists(Long.valueOf(id))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/customer/existsByDocumentId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> existsByDocumentId(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, "", customerRepository.existsByIdDocument(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/customer/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<List<ApiCustomer>>> getAllCustomers() {
        try {
            String message = "Success.";
            HttpStatus status = HttpStatus.OK;
            List<ApiCustomer> apiCustomers = new ArrayList<>();
            for (Customer c : customerRepository.findAll()) {
                apiCustomers.add(customerBuilder.createApiCustomer(c));
            }
            return new ResponseEntity<>(new ApiResponse<>(true, message, apiCustomers), status);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/customer/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ApiResponse<Long>> getCountCustomers() {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, "Success.", customerRepository.count()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error: " + e.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SimpleApiResponse> createCustomer(@RequestBody ApiRequest<ApiCustomer> apiCustomer) {
        try {
            String message;
            boolean success;
            ValidationResult valid = customerValidator.validateCustomer(apiCustomer.getPayload());
            if (valid.getIsValid()) {
                Customer customer = customerBuilder.createCustomer(apiCustomer.getPayload());
                customerRepository.save(customer);
                success = true;
                message = "Created customer id=" + customer.getId() + ".";
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

    @RequestMapping(value = "/technicaltest/api/customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SimpleApiResponse> updateCustomer(@RequestBody ApiRequest<ApiCustomer> apiCustomer) {
        try {
            String message;
            Boolean success;
            HttpStatus status;
            ApiCustomer payload = apiCustomer.getPayload();
            Customer customer = customerRepository.findOne(payload.getId());
            if (customer == null) {
                // usuario no existe
                message = "Not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                // usuario existe se trata de actualizar
                ValidationResult valid = customerValidator.validateCustomer(apiCustomer.getPayload());
                if (valid.getIsValid()) {
                    success = true;
                    customerRepository.save(customerBuilder.updateCustomer(customer, payload));
                    message = "Updated.";
                } else {
                    success = false;
                    message = "Validation Errors: " + valid.getValidationErrors().stream().reduce("", (s1, s2) -> s1 + "- " + s2 + ".\r\n") + ".'";
                }
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(new SimpleApiResponse(true, message), status);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleApiResponse(false, "Can't update user. Internal Server Error: " + e.toString()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/technicaltest/api/customer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SimpleApiResponse> delete(@PathVariable("id") String id) {
        try {
            String message;
            HttpStatus status;
            Customer customer = customerRepository.findOne(Long.valueOf(id));

            if (customer == null) {
                // usuario no existe
                message = "Not found.";
                status = HttpStatus.NOT_FOUND;
            } else {
                // usuario existe se trata de actualizar
                customerRepository.delete(Long.valueOf(id));
                message = "Deleted.";
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(new SimpleApiResponse(true, message), status);
        } catch (Exception e) {
            return new ResponseEntity<>(new SimpleApiResponse(false, "Can't delete user. Internal Server Error: " + e.toString()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
