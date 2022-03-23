package com.lafinance.dashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "acao")
public class Acao implements Serializable {

    private static final long serialVersionUID = 1559264000581873246L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ativo", nullable = false)
    private Ativo ativo;

    @NotNull
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @NotNull
    @Column(name = "valorbrutopago", nullable = false)
    private BigDecimal valorBrutoPago;

    @NotNull
    @Column(name = "valorativopago", nullable = false)
    private BigDecimal valorAtivoPago;

    @NotNull
    @Column(name = "datacompra", nullable = false)
    private LocalDate dataCompra;

    @NotNull
    @Column(name = "datacriacao", nullable = false)
    private LocalDate mesCriacao;

    @Column(name = "dataatualizacao")
    private LocalDate mesAtualizacao;

    @Column(name = "status")
    private String status;

    @Column(name = "precoalvo")
    private BigDecimal precoAlvo;

    @Column(name = "precohoje")
    private BigDecimal precoHoje;

    public Acao() {
    }
}