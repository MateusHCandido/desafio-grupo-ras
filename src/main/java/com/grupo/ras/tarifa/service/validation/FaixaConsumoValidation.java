package com.grupo.ras.tarifa.service.validation;

import com.grupo.ras.tarifa.controller.dto.FaixaConsumoRequest;


import java.util.List;

public interface FaixaConsumoValidation {
    void validar(Long categoriaId, FaixaConsumoRequest request);
}
