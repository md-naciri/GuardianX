package com.guardianx.exception.customexceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InValidRefreshTokenException extends Exception {
    private final String message;
}
