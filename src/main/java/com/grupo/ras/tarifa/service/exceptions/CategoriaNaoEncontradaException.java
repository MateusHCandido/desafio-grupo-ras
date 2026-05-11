package com.grupo.ras.tarifa.service.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(){
        super("categoria não encontrada");
    }
}
