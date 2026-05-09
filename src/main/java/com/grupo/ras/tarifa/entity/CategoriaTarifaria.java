package com.grupo.ras.tarifa.entity;

import com.grupo.ras.tarifa.enums.CategoriaConsumidor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaTarifaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaTarifariaId;

    @Enumerated(EnumType.STRING)
    private CategoriaConsumidor categoriaConsumidor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tabela_tarifaria_id")
    private TabelaTarifaria tabelaTarifaria;

    @OneToMany(mappedBy = "categoriaTarifaria",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<FaixaConsumo> faixas = new ArrayList<>();
}
