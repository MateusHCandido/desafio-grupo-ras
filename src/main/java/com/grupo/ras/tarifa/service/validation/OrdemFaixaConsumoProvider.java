package com.grupo.ras.tarifa.service.validation;

import com.grupo.ras.tarifa.controller.dto.FaixaConsumoRequest;
import com.grupo.ras.tarifa.service.exceptions.OrdemFaixasInvalidaException;
import org.springframework.stereotype.Component;

@Component
public class OrdemFaixaConsumoProvider implements FaixaConsumoValidation {

    @Override
    public void validar(Long categoriaId, FaixaConsumoRequest request) {
        if (request.faixaInicial() >= request.faixaFinal()) throw new OrdemFaixasInvalidaException();
    }
}
