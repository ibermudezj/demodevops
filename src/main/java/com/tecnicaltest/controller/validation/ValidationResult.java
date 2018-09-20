package com.tecnicaltest.controller.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private Boolean isValid = false;
    
    private List<String> validationErrors = new ArrayList<>();

    public ValidationResult(Boolean isValid, List<String> validationErrors) {
        super();
        this.isValid = isValid;
        this.validationErrors = validationErrors;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
    
    
}
