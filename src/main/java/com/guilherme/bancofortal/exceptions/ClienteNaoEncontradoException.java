package com.guilherme.bancofortal.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
