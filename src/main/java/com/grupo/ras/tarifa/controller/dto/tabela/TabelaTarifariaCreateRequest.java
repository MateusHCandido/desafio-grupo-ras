package com.grupo.ras.tarifa.controller.dto.tabela;


import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaCreateRequest(
        String nome,
        LocalDate dataVigencia,
        List<CategoriaRequest> categorias
) { }
