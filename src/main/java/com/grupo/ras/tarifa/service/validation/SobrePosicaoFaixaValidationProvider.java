package com.grupo.ras.tarifa.service.validation;

import com.grupo.ras.tarifa.controller.dto.FaixaConsumoRequest;
import com.grupo.ras.tarifa.repository.provider.FaixaConsumoRepositoryProvider;
import com.grupo.ras.tarifa.service.exceptions.SobreposicaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SobrePosicaoFaixaValidationProvider implements FaixaConsumoValidation {

    private final FaixaConsumoRepositoryProvider faixaConsumoRepository;


    @Override
    public void validar(Long categoriaId, FaixaConsumoRequest request) {
        boolean existeSobrePosicao = faixaConsumoRepository
                .existsSobreposicao(
                        categoriaId,
                        request.faixaInicial(),
                        request.faixaFinal()
                );

        if (existeSobrePosicao) throw new SobreposicaoException();
    }
}
