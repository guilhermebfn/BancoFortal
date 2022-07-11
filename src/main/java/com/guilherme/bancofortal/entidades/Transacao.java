package com.guilherme.bancofortal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotNull
    private BigDecimal valor;

    @JoinColumn
    @OneToOne
    @JsonIgnore
    private Cliente pagador;

    @JoinColumn
    @OneToOne
    @JsonIgnore
    private Cliente recebedor;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public Transacao() {
    }

    public Transacao(BigDecimal valor, Cliente pagador, Cliente recebedor, LocalDateTime dataHora) {
        this.valor = valor;
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Cliente getPagador() {
        return pagador;
    }

    public void setPagador(Cliente pagador) {
        this.pagador = pagador;
    }

    public Cliente getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Cliente recebedor) {
        this.recebedor = recebedor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
