package com.grupo.ras.tarifa.controller.dto.tabela;

import java.math.BigDecimal;

public record FaixaRequest(
        Integer faixaInicio,
        Integer faixaFim,
        BigDecimal valor
) { }
