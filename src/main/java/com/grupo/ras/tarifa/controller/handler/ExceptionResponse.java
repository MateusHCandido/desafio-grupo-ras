package com.grupo.ras.tarifa.controller.handler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) { }