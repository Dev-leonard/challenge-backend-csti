package com.csti.challengebackend.application.dto.response;

import com.csti.challengebackend.infrastructure.exception.ExceptionDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @param <T> Generic type that contains the particular request data
 * @author lmr
 * <p>
 * Generic and standard  way to form a request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    /**
     *
     */
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 5083442142904199681L;
    /**
     * Contains the specific response data object.
     */
    private T data;

    /**
     * Indicate the operation was a success true or have errors false
     */
    private boolean success;
    /**
     * Exception detail if exist.
     */
    private ExceptionDetail exception;
}
