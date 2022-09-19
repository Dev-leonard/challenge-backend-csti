package com.csti.challengebackend.infrastructure.exception;

import com.csti.challengebackend.application.dto.response.GenericResponse;
import com.csti.challengebackend.infrastructure.exception.custom.InternalServerException;
import com.csti.challengebackend.infrastructure.exception.custom.UnauthorizedException;
import com.csti.challengebackend.infrastructure.exception.custom.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;


@RestControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private ObjectMapper objectMapper;

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class})
    public GenericResponse<Object> handleNotFoundException(AbstractException e) {
        log.error("[ErrorHandler:handleNotFoundException] " + e.getErrorMessage());
        return this.parseHandledException(e);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public GenericResponse<Object> handleUnauthorizedException(AbstractException e) {
        log.error("[ErrorHandler:handleNotFoundException] " + e.getErrorMessage());
        return this.parseHandledException(e);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerException.class})
    public GenericResponse<Object> handleInternalErrorException(AbstractException e) {
        log.error("[ErrorHandler:handleNotFoundException] " + e.getErrorMessage());
        return this.parseHandledException(e);
    }

    private GenericResponse<Object> parseHandledException(AbstractException ex) {
        log.error("[ErrorHandler:parseHandledException] error: {}, detail:{}", ex.getErrorMessage(), ex.getErrorDetail());
        return GenericResponse
                .builder()
                .success(false)
                .exception(ExceptionDetail.builder()
                        .errorMessage(ex.getErrorMessage())
                        .detailError(ex.getErrorDetail() != null ? ex.getErrorDetail() : (Objects.nonNull(ex.getCause()) ? ex.getCause().toString() : null))
                        .timestamp(ex.getTimestamp())
                        .build())
                .build();
    }
}
