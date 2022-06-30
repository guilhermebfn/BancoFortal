package com.guilherme.bancofortal.entidades;

import javax.persistence.*;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column
    private String senha;
}
