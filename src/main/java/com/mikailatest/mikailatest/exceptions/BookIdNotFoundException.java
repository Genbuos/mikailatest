package com.mikailatest.mikailatest.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookIdNotFoundException extends EntityNotFoundException {
    public BookIdNotFoundException() {
    }

    public BookIdNotFoundException(Exception cause) {
        super(cause);
    }

    public BookIdNotFoundException(String message) {
        super(message);
    }

    public BookIdNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
