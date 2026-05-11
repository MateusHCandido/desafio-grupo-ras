package com.grupo.ras.tarifa.repository.provider;

import com.grupo.ras.tarifa.entity.TabelaTarifaria;
import com.grupo.ras.tarifa.enums.StatusTabelaTarifaria;
import com.grupo.ras.tarifa.repository.adapter.TabelaTarifariaRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TabelaTarifariaRepositoryProvider {

    private final TabelaTarifariaRepositoryAdapter tabelaTarifariaRepository;

    public void save(TabelaTarifaria tabelaTarifaria) {
        tabelaTarifariaRepository.save(tabelaTarifaria);
    }

    public List<TabelaTarifaria> findAll(){
        return tabelaTarifariaRepository.findAll();
    }

    public void deleteBy(Long tabelaTarifariaId){
        TabelaTarifaria tabelaTarifaria = tabelaTarifariaRepository.getReferenceById(tabelaTarifariaId);
        tabelaTarifariaRepository.delete(tabelaTarifaria);
    }

    public Optional<TabelaTarifaria> findByStatus(StatusTabelaTarifaria statusTabelaTarifaria) {
        return tabelaTarifariaRepository.findByStatus(statusTabelaTarifaria);
    }

    public void inativarTabelasAtivas(){
        tabelaTarifariaRepository.inativaTabelasAtivas(StatusTabelaTarifaria.INATIVA);
    }
}
