package com.etnetera.hr.rest;

import java.util.Date;

/**
 * Entity not found error. Represents JSON response.
 *
 * @author Tom
 */
public class EntityNotFoundError {

    public Date timestamp;
    public String error;

    public EntityNotFoundError(Date timestamp, String error) {
        this.timestamp = timestamp;
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
