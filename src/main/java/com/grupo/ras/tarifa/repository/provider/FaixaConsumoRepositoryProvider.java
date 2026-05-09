package com.grupo.ras.tarifa.repository.provider;

import com.grupo.ras.tarifa.entity.CategoriaTarifaria;
import com.grupo.ras.tarifa.repository.adapter.FaixaConsumoRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FaixaConsumoRepositoryProvider {

    private final FaixaConsumoRepositoryAdapter faixaConsumoRepository;

    public boolean existByCategoriaTarifaria(CategoriaTarifaria categoriaTarifaria) {
        log.info("Iniciando verificando a busca de categoria tarifaria na classe FaixaConsumoRepositoryProvider");
        return faixaConsumoRepository.existsByCategoriaTarifaria(categoriaTarifaria);
    }

    public boolean existsSobreposicao(Long categoriaTarifariaId, Integer faixaInicial, Integer faixaFinal) {
        log.info("Validando se existe sobreposição na classe FaixaConsumoRepositoryProvider");
        return faixaConsumoRepository.existsSobreposicao(categoriaTarifariaId, faixaInicial, faixaFinal);
    }
}
