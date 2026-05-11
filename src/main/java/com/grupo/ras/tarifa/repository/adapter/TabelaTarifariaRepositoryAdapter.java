package com.grupo.ras.tarifa.repository.adapter;

import com.grupo.ras.tarifa.entity.TabelaTarifaria;
import com.grupo.ras.tarifa.enums.StatusTabelaTarifaria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TabelaTarifariaRepositoryAdapter extends JpaRepository<TabelaTarifaria, Long> {
    Optional<TabelaTarifaria> findByStatus(StatusTabelaTarifaria statusTabelaTarifaria);

    @Modifying
    @Transactional
    @Query("""
            UPDATE TabelaTarifaria t
            SET t.status = :status
            WHERE t.status = 'ATIVA'
            """)
    void inativaTabelasAtivas(StatusTabelaTarifaria status);

}
