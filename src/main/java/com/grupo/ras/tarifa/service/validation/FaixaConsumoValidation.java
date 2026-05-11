package com.grupo.ras.tarifa.service.validation;

import com.grupo.ras.tarifa.controller.dto.tabela.FaixaConsumoRequest;

public interface FaixaConsumoValidation {
    void validar(Long categoriaId, FaixaConsumoRequest request);
}
