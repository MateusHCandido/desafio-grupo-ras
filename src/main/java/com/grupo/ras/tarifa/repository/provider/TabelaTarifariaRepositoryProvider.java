package com.grupo.ras.tarifa.repository.provider;

import com.grupo.ras.tarifa.entity.TabelaTarifaria;
import com.grupo.ras.tarifa.repository.adapter.TabelaTarifariaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TabelaTarifariaRepositoryProvider {

    private final TabelaTarifariaRepositoryAdapter tabelaTarifariaRepository;

    public void save(TabelaTarifaria tabelaTarifaria) {
        tabelaTarifariaRepository.save(tabelaTarifaria);
    }
}
