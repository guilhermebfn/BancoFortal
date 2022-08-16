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
    @NotEmpty(message = "O campo nome de usuário é obrigatório")
    private String nomeUsuario;

    @Column
    @NotEmpty(message = "O campo senha é obrigatório")
    private String senha;

    public Usuario() {
    }

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
