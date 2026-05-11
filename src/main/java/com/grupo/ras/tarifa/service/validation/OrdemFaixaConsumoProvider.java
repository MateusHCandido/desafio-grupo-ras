package com.grupo.ras.tarifa.service.validation;

import com.grupo.ras.tarifa.controller.dto.tabela.FaixaConsumoRequest;
import com.grupo.ras.tarifa.service.exceptions.OrdemFaixasInvalidaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrdemFaixaConsumoProvider implements FaixaConsumoValidation {

    @Override
    public void validar(Long categoriaId, FaixaConsumoRequest request) {
        log.info("Validando ORDEM FAIXA CONSUMO");
        if (request.faixaInicial() >= request.faixaFinal()) throw new OrdemFaixasInvalidaException();
    }
}
