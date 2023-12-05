package com.mikailatest.mikailatest.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryIdNotFoundException extends EntityNotFoundException {
    public CategoryIdNotFoundException() {
    }

    public CategoryIdNotFoundException(Exception cause) {
        super(cause);
    }

    public CategoryIdNotFoundException(String message) {
        super(message);
    }

    public CategoryIdNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
