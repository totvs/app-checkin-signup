package com.tjf.checkin.signup.exception;

import javax.persistence.NonUniqueResultException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

/**
 * The Class ParticipantNonUniqueResultException.
 * 
 * <br>Error occurs in the request, if a participant is not unique.
 * 
 * <br>{@link ApiBadRequest}
 * 
 * @author Marcos Paulo dos Santos
 */
@ApiBadRequest("ParticipantNonUniqueResultException")
public class ParticipantNonUniqueResultException extends NonUniqueResultException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4240614994020766468L;
}