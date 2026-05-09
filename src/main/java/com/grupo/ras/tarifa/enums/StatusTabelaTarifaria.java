package com.grupo.ras.tarifa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusTabelaTarifaria {

    ATIVA("ATIVA"),
    INATIVA("INATIVA");

    private final String descricao;
}