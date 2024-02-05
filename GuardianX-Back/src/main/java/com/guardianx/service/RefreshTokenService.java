package com.guardianx.service;

import com.guardianx.domain.entity.RefreshToken;
import com.guardianx.exception.customexceptions.InValidRefreshTokenException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface RefreshTokenService {
    RefreshToken getOrCreateRefreshToken(String email) throws UsernameNotFoundException;
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token) throws InValidRefreshTokenException;
    void throwInValidRefreshTokenException(String message) throws InValidRefreshTokenException;
    void delete(RefreshToken token);
}
