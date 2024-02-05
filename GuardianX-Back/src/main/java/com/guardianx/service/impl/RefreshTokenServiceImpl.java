package com.guardianx.service.impl;

import com.guardianx.domain.entity.RefreshToken;
import com.guardianx.domain.entity.User;
import com.guardianx.exception.customexceptions.InValidRefreshTokenException;
import com.guardianx.repository.RefreshTokenRepository;
import com.guardianx.repository.UserRepository;
import com.guardianx.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Component
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${jwt.access.expirationInMs}")
    private Long accessTokenExpirationInMs;
    @Value("${jwt.refresh.expirationInMonths}")
    private Long refreshTokenExpirationInMonths;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenServiceImpl(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    @Override
    public RefreshToken getOrCreateRefreshToken(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found "));
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser(user.getId());
        if(optionalRefreshToken.isPresent())
            return optionalRefreshToken.get();
        LocalDateTime accessTokenExpirationDateTime = LocalDateTime.now().plusMinutes(this.accessTokenExpirationInMs / 60000); // 60000 ms = 1 minute
        LocalDateTime refreshTokenExpirationDateTime = accessTokenExpirationDateTime.plusMonths(this.refreshTokenExpirationInMonths);
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(refreshTokenExpirationDateTime)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) throws InValidRefreshTokenException {
        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(refreshToken);
            throwInValidRefreshTokenException("Refresh token was expired. Please make a new signin.");
        }
        return refreshToken;
    }

    @Override
    public void delete(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }

    @Override
    public void throwInValidRefreshTokenException(String message) throws InValidRefreshTokenException {
        throw new InValidRefreshTokenException(message);
    }
}
