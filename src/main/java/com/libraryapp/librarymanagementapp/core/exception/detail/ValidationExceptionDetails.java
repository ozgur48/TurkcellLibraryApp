package com.libraryapp.librarymanagementapp.core.exception.detail;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationExceptionDetails extends ExceptionDetails{
    private List<ObjectError> validationErrors;

    public List<ObjectError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ObjectError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationExceptionDetails(String message, List<ObjectError> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }
}
