package com.grupo.ras.tarifa.service.exceptions;

public class CoberturaNaoCompletaException extends RuntimeException {
    public CoberturaNaoCompletaException(){
        super("Não existem faixas iniciando em 0");
    }
}
