package com.guilherme.bancofortal.dto;

public class TokenDTO {

    private String nomeUsuario;
    private String token;

    public TokenDTO(String nomeUsuario, String token) {
        this.nomeUsuario = nomeUsuario;
        this.token = token;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getToken() {
        return token;
    }
}
