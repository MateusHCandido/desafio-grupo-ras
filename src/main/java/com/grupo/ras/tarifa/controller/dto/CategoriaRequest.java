package com.grupo.ras.tarifa.controller.dto;

import java.util.List;

public record CategoriaRequest(
        String categoria,
        List<FaixaRequest> faixas
) {
}
