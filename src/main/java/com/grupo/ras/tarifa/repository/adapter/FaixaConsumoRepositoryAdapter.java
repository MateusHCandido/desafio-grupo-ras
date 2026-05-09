package com.grupo.ras.tarifa.repository.adapter;

import com.grupo.ras.tarifa.entity.CategoriaTarifaria;
import com.grupo.ras.tarifa.entity.FaixaConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaixaConsumoRepositoryAdapter extends JpaRepository<FaixaConsumo, Long> {
    boolean existsByCategoriaTarifaria(CategoriaTarifaria categoriaTarifaria);

    @Query("""
            SELECT
                CASE WHEN COUNT(f) > 0 THEN true ELSE false END
            FROM FaixaConsumo f
            WHERE f.categoriaTarifaria.categoriaTarifariaId = :categoriaId
                AND :faixaInicial <= f.faixaFinal
                AND :faixaFinal >= f.faixaInicial
            """)
    boolean existsSobreposicao(@Param("categoriaId") Long categoriaId,
                               @Param("faixaInicial") Integer faixaInicial,
                               @Param("faixaFinal") Integer faixaFinal);
}
