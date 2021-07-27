package com.etnetera.hr.controller;

import com.etnetera.hr.rest.EntityNotFoundError;
import com.etnetera.hr.rest.Errors;
import com.etnetera.hr.rest.ValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Main REST controller.
 *
 * @author Etnetera
 */
public abstract class EtnRestController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Errors errors = new Errors();
        List<ValidationError> errorList = result.getFieldErrors().stream().map(e -> {
            return new ValidationError(e.getField(), e.getCode());
        }).collect(Collectors.toList());
        errors.setErrors(errorList);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Errors> handleValidationException(EntityNotFoundException ex) {
        Errors errors = new Errors();
        errors.setEntityNotFoundError(new EntityNotFoundError(new Date(), ex.getMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    public static <T> ResponseEntity<T> ok() {
        return new ResponseEntity<>(OK);
    }

    public static <T> ResponseEntity<T> ok(T to) {
        return new ResponseEntity<>(to, OK);
    }

    public static <T> ResponseEntity<T> created() {
        return new ResponseEntity<>(CREATED);
    }

}
