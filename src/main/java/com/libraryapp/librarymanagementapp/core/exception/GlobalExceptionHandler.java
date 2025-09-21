package com.libraryapp.librarymanagementapp.core.exception;

import com.libraryapp.librarymanagementapp.core.exception.detail.ExceptionDetails;
import com.libraryapp.librarymanagementapp.core.exception.detail.ValidationExceptionDetails;
import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDetails handleValidationException(MethodArgumentNotValidException ex){
        return new ValidationExceptionDetails("Validasyon hatası", ex.getAllErrors());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRunTimeException(){
        return "Runtime Error";
    }

    // her ex handler fırlayan ex'ı parametre olarak alır
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDetails handleBusinessException(BusinessException e){
        return new ExceptionDetails(e.getMessage());
    }


}
