package com.etnetera.hr.rest;

import java.util.List;

/**
 * Envelope for the validation errors. Represents JSON response.
 *
 * @author Etnetera
 */
public class Errors {

    private List<ValidationError> errors;

    private EntityNotFoundError entityNotFoundError;

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public EntityNotFoundError getEntityNotFoundError() {
        return entityNotFoundError;
    }

    public void setEntityNotFoundError(EntityNotFoundError entityNotFoundError) {
        this.entityNotFoundError = entityNotFoundError;
    }
}
