package com.example.productservice.exception;

import com.example.productservice.exception.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> internalException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Internal server error", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Not found", e.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<Object> InvalidException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Invalid exception", e.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> UnauthorizedException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Unauthorized", e.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }
}
