package com.grupo.ras.tarifa.controller.dto.calculo;

import com.grupo.ras.tarifa.enums.CategoriaConsumidor;


public record CalculoDTORequest(CategoriaConsumidor categoria, Integer consumo) {}
