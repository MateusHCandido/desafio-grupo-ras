package com.grupo.ras.tarifa.controller;

import com.grupo.ras.tarifa.controller.dto.calculo.CalculoDTORequest;
import com.grupo.ras.tarifa.controller.dto.calculo.CalculoTarifaResponse;
import com.grupo.ras.tarifa.service.CalculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/calculos")
@RequiredArgsConstructor
public class CalculoController {

    private final CalculoService calculoService;

    @PostMapping
    public ResponseEntity<CalculoTarifaResponse> calcularValorAPagar(@RequestBody CalculoDTORequest request){
        return ResponseEntity.ok(calculoService.calcular(request.categoria(), request.consumo()));
    }
}
