package com.tecnicaltest.dto.business;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class ApiInvoice {
	
	private Long id;

	private Long customerId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private Long number;

    private String address;

    private String product;

    private Long quantity;

    private BigDecimal total;

    private BigDecimal taxes;

    private BigDecimal grandTotal;
    
    public ApiInvoice() {
        
    }
    
    public ApiInvoice(Long id, Long customerId, Date date, Long number, String address, String product, Long quantity, BigDecimal total, BigDecimal taxes,
            BigDecimal grandTotal) {
        super();
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.number = number;
        this.address = address;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.taxes = taxes;
        this.grandTotal = grandTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
	
}
