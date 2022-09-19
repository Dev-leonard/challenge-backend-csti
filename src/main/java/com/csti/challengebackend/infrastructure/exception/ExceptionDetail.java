package com.csti.challengebackend.infrastructure.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Contains user friendly detail exception
 *
 * @author lmr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDetail implements Serializable {
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final long serialVersionUID = -1322383971953179312L;
    /**
     * Internal Error code of the exception
     */
    private String errorCode;
    /**
     * User friendly error detail message
     */
    private String errorMessage;

    private String detailError;

    private ZonedDateTime timestamp;
}
