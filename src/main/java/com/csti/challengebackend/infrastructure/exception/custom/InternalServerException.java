package com.csti.challengebackend.infrastructure.exception.custom;

import com.csti.challengebackend.infrastructure.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends AbstractException {
    public InternalServerException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
