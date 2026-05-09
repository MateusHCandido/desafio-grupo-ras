package com.grupo.ras.tarifa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FaixaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faixaId;
    private String faixaDescricao;
    private Integer faixaInicial;
    private Integer faixaFinal;
    private BigDecimal faixaValorUnitario;

    @ManyToOne
    @JoinColumn(name = "categoria_tarifaria_id")
    private CategoriaTarifaria categoriaTarifaria;
}
