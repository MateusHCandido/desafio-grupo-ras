package com.grupo.ras.tarifa.controller.dto.calculo;

import lombok.Builder;

@Builder
public record FaixaResponse(
        Integer inicio,
        Integer fim
) { }
