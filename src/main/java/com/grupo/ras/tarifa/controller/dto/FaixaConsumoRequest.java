package com.grupo.ras.tarifa.controller.dto;

import java.math.BigDecimal;

public record FaixaConsumoRequest(
        Integer faixaInicial,
        Integer faixaFinal,
        BigDecimal valor
) {
}
