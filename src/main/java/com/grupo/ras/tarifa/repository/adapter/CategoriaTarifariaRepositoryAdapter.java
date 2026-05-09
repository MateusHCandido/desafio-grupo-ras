package com.grupo.ras.tarifa.repository.adapter;

import com.grupo.ras.tarifa.entity.CategoriaTarifaria;
import com.grupo.ras.tarifa.enums.CategoriaConsumidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaTarifariaRepositoryAdapter extends JpaRepository<CategoriaTarifaria, Long> {
    List<CategoriaTarifaria> findByCategoriaConsumidor(CategoriaConsumidor categoriaConsumidor);
}
