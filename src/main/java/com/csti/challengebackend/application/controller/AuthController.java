package com.csti.challengebackend.application.controller;

import com.csti.challengebackend.application.dto.request.LoginRequest;
import com.csti.challengebackend.application.dto.response.AuthenticationResponseDto;
import com.csti.challengebackend.application.dto.response.GenericResponse;
import com.csti.challengebackend.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<AuthenticationResponseDto> userLogin(@RequestBody LoginRequest loginRequest) {
        log.info("[AuthController:userLogin]: LoginRequest: {} ", loginRequest);
        final var response = new GenericResponse<AuthenticationResponseDto>();
        response.setSuccess(true);
        response.setData(authService.userLogin(loginRequest));
        return response;
    }


}
