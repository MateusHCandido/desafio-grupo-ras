package com.grupo.ras.tarifa.service.exceptions;

public class SemRegistroTabelaException extends RuntimeException {
    public SemRegistroTabelaException(){
        super("Não há registro de tabelas tarifárias");
    }
}
