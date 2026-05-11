package com.grupo.ras.tarifa.controller.dto.calculo;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DetalhamentoFaixaResponse(
        FaixaResponse faixa,
        Integer m3Cobrados,
        BigDecimal valorUnitario,
        BigDecimal subtotal
) { }
