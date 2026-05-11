package com.grupo.ras.tarifa.controller.dto.tabela;

import java.util.List;

public record CategoriaRequest(
        String categoria,
        List<FaixaRequest> faixas
) { }
