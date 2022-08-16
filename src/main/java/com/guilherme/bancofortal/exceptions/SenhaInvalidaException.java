package com.guilherme.bancofortal.exceptions;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
