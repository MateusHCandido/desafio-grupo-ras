package com.grupo.ras.tarifa.service.exceptions;

public class OrdemFaixasInvalidaException extends RuntimeException {
    public OrdemFaixasInvalidaException() {
        super("A faixa de início não pode ser maior que a faixa final");
    }
}
