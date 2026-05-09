package com.grupo.ras.tarifa.repository.provider;

import com.grupo.ras.tarifa.entity.CategoriaTarifaria;
import com.grupo.ras.tarifa.enums.CategoriaConsumidor;
import com.grupo.ras.tarifa.repository.adapter.CategoriaTarifariaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryProvider {

    private final CategoriaTarifariaRepositoryAdapter categoriaTarifariaRepository;

    public List<CategoriaTarifaria> findByCategoriaConsumidor(CategoriaConsumidor categoriaConsumidor){
        log.info("Iniciando verificando a busca de consumidor por categoria");
        return categoriaTarifariaRepository.findByCategoriaConsumidor(categoriaConsumidor);
    }
}
