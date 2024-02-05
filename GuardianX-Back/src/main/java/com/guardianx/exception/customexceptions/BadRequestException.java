package com.guardianx.exception.customexceptions;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends Exception {
    private final String message;
}
