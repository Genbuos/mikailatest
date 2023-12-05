package com.mikailatest.mikailatest.exceptions.handler;

import com.mikailatest.mikailatest.exceptions.BookIdNotFoundException;
import com.mikailatest.mikailatest.exceptions.CategoryIdNotFoundException;
import com.mikailatest.mikailatest.exceptions.NoContentException;
import com.mikailatest.mikailatest.exceptions.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookIdNotFoundException.class)
public ResponseEntity<?> handleBookIdNotFound(BookIdNotFoundException bookIdNotFoundException){
        ErrorDetail detail = new ErrorDetail(HttpStatus.NOT_FOUND.value(), bookIdNotFoundException.getMessage());
        return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(CategoryIdNotFoundException.class)
public ResponseEntity<?> handleCatIdNotFound(CategoryIdNotFoundException categoryIdNotFoundException){
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.NOT_FOUND.value(), categoryIdNotFoundException.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
}

public ResponseEntity<?> handleNoContent(NoContentException noContentException){
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.NO_CONTENT.value(), noContentException.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.NO_CONTENT);
}


}
