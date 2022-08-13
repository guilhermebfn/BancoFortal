package com.guilherme.bancofortal.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_usuario")
    @NotEmpty(message = "{campo.nome-usuario.obrigatorio}")
    private String nomeUsuario;

    @Column
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    public Usuario(Integer id, String nomeUsuario, String senha) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }
}
