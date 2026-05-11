package com.grupo.ras.tarifa.controller;

import com.grupo.ras.tarifa.controller.dto.tabela.TabelaTarifariaCreateRequest;
import com.grupo.ras.tarifa.entity.TabelaTarifaria;
import com.grupo.ras.tarifa.service.TabelaTarifariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tabelas-tarifarias")
@RequiredArgsConstructor
public class TabelaTarifariaController {

    private final TabelaTarifariaService service;

    @PostMapping
    public ResponseEntity criarTabelaTarifaria(@RequestBody TabelaTarifariaCreateRequest request){
        service.criarTabelaTarifaria(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<TabelaTarifaria>> listarTabelasTarifarias(){
        return ResponseEntity.ok(service.listarTabelasTarifaria());
    }

    @DeleteMapping("{id}")
    public void deletarTabelaTarifaria(@PathVariable("id") Long tabelaTarifariaId){
        service.excluirTabelaTarifaria(tabelaTarifariaId);
    }
}
