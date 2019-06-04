package com.tjf.checkin.signup.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

/**
 * The Class ParticipantContraintException.
 * 
 * <br>An error occurs in the request, if there is any violation.
 * 
 * <br>{@link ApiBadRequest}
 * 
 * @author Marcos Paulo dos Santos
 */
@ApiBadRequest("ParticipantContraintException")
public class ParticipantContraintException extends ConstraintViolationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2071517040052831080L;

    /**
     * Instantiates a new participant contraint exception.
     *
     * @param constraintViolations the constraint violations
     */
    public ParticipantContraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}