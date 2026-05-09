package com.grupo.ras.tarifa.service.exceptions;

public class SobreposicaoException extends RuntimeException {
    public SobreposicaoException(){
        super("Faixas de intervalo se cruzam");
    }
}
