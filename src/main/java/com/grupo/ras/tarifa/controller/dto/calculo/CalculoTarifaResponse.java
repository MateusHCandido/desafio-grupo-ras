package com.grupo.ras.tarifa.controller.dto.calculo;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CalculoTarifaResponse(
        String categoria,
        Integer consumoTotal,
        BigDecimal valorTotal,
        List<DetalhamentoFaixaResponse> detalhamento
) {}
