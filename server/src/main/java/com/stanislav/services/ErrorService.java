package com.stanislav.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author Stanislav Hlova
 */
@Service
public class ErrorService {
    public String getErrorMessage(BindingResult bindingResult) {
        StringBuilder messageBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messageBuilder.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append(";");
        }
        return messageBuilder.toString();
    }
}
