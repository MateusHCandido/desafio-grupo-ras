package com.grupo.ras.tarifa.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faixaId;
    @JsonIgnore
    private String faixaDescricao;
    private Integer faixaInicial;
    private Integer faixaFinal;
    private BigDecimal faixaValorUnitario;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_tarifaria_id")
    private CategoriaTarifaria categoriaTarifaria;
}
