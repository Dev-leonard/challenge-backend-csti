package com.csti.challengebackend.infrastructure.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Getter
@NoArgsConstructor
public class ApplicationProperties {

    @Value("${csti.app.jwtSecret}")
    private String jwtSecret;

    @Value("${csti.app.jwtExpirationMs}")
    private int jwtExpirationMs;
}
