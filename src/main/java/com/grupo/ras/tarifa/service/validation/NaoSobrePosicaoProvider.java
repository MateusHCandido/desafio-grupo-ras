package com.grupo.ras.tarifa.service.validation;

import com.grupo.ras.tarifa.controller.dto.FaixaConsumoRequest;
import com.grupo.ras.tarifa.repository.provider.FaixaConsumoRepositoryProvider;
import com.grupo.ras.tarifa.service.exceptions.SobreposicaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NaoSobrePosicaoProvider implements FaixaConsumoValidation {

    private final FaixaConsumoRepositoryProvider faixaConsumoRepository;

    @Override
    public void validar(Long categoriaId, FaixaConsumoRequest request) {
        if (categoriaId == null) throw new RuntimeException("CategoriaId nula no método de validação da não sobreposição");

        boolean existeSobreposicao =
                faixaConsumoRepository.existsSobreposicao(
                        categoriaId,
                        request.faixaInicial(),
                        request.faixaFinal()
                );

        if (existeSobreposicao) throw new SobreposicaoException();
    }

}
