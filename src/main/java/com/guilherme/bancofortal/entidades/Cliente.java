package com.guilherme.bancofortal.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotEmpty
    private String nome;

    @Column
    private BigDecimal saldo;

    @OneToMany(mappedBy = "pagador")
    private List<Transacao> transferenciasFeitas = new ArrayList<>();

    @OneToMany(mappedBy = "recebedor")
    private List<Transacao> transferenciasRecebidas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(String nome, BigDecimal saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransferenciasFeitas() {
        return transferenciasFeitas;
    }

    public void setTransferenciasFeitas(List<Transacao> transferenciasFeitas) {
        this.transferenciasFeitas = transferenciasFeitas;
    }

    public List<Transacao> getTransferenciasRecebidas() {
        return transferenciasRecebidas;
    }

    public void setTransferenciasRecebidas(List<Transacao> transferenciasRecebidas) {
        this.transferenciasRecebidas = transferenciasRecebidas;
    }
}
