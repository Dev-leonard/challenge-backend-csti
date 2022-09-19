package com.csti.challengebackend.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractException extends RuntimeException{
    private String errorMessage;
    private String errorDetail;
    private ZonedDateTime timestamp;
    private HttpStatus httpStatus;

    public AbstractException(String errorDescription, HttpStatus httpStatus) {
        this.errorMessage = errorDescription;
        this.httpStatus = httpStatus;
        this.timestamp = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
    }

    public AbstractException(String errorDescription, String errorDetail, HttpStatus httpStatus) {
        this.errorMessage = errorDescription;
        this.errorDetail = errorDetail;
        this.httpStatus = httpStatus;
        this.timestamp = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
    }
}
