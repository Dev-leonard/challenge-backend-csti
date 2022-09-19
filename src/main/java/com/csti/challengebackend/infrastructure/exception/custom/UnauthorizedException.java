package com.csti.challengebackend.infrastructure.exception.custom;

import com.csti.challengebackend.infrastructure.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AbstractException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
