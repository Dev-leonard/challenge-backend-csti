package com.csti.challengebackend.infrastructure.Utils;

import com.csti.challengebackend.infrastructure.exception.custom.InternalServerException;
import com.csti.challengebackend.infrastructure.exception.custom.UnauthorizedException;
import com.csti.challengebackend.infrastructure.properties.ApplicationProperties;
import com.csti.challengebackend.infrastructure.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;

@Component
@Slf4j
@AllArgsConstructor
public class JwtUtils {

    private ApplicationProperties applicationProperties;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + applicationProperties.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getJwtSecret())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(applicationProperties.getJwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(applicationProperties.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            throw new UnauthorizedException("Invalid JWT signature:");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new UnauthorizedException("Invalid JWT token: {}");
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw new UnauthorizedException("JWT token is expired: {}");
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            throw new UnauthorizedException("JWT token is unsupported: {}");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            throw new UnauthorizedException("JWT claims string is empty: {}");
        }
    }

    public String getUsernameFromCurrentSession() {
        try {
            final Object currentUserName = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Class<?> clazz = currentUserName.getClass();
            Field field = org.springframework.util.ReflectionUtils.findField(clazz, "username");
            org.springframework.util.ReflectionUtils.makeAccessible(field);
            return field.get(currentUserName).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalServerException("this value not found");
        }
    }


}
