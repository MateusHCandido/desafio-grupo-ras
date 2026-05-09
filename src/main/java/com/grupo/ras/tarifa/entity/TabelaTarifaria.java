package com.grupo.ras.tarifa.entity;

import com.grupo.ras.tarifa.enums.StatusTabelaTarifaria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TabelaTarifaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tabelaTarifariaId;

    private String nome;

    private LocalDate dataVigencia;

    @Enumerated(EnumType.STRING)
    private StatusTabelaTarifaria status;

    @OneToMany(mappedBy = "tabelaTarifaria",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<CategoriaTarifaria> categorias = new ArrayList<>();
}
