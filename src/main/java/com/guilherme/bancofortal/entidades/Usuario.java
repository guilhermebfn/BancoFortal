package com.guilherme.bancofortal.entidades;

import javax.persistence.*;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nomeUsuario;

    @Column
    private String senha;
}
