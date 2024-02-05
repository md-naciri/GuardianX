package com.guardianx.service;

import com.guardianx.domain.dto.response.auth.JwtAuthenticationResponseDto;
import com.guardianx.domain.entity.User;
import com.guardianx.exception.customexceptions.BadRequestException;
import com.guardianx.exception.customexceptions.ValidationException;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponseDto signup(User request) throws ValidationException;
    JwtAuthenticationResponseDto signin(User request) throws BadRequestException;
}
