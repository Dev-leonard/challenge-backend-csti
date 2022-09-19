package com.csti.challengebackend.service;

import com.csti.challengebackend.application.dto.request.LoginRequest;
import com.csti.challengebackend.application.dto.response.AuthenticationResponseDto;

public interface AuthService {

    AuthenticationResponseDto userLogin(LoginRequest loginRequest);
}
