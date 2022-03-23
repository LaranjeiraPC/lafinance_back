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

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Venda implements Serializable {

    private static final long serialVersionUID = -3574568785078311013L;

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
    @Column(name = "valorbrutovenda", nullable = false)
    private BigDecimal valorBrutoVenda;

    @NotNull
    @Column(name = "valorativovenda", nullable = false)
    private BigDecimal valorAtivoVenda;

    @NotNull
    @Column(name = "datavenda", nullable = false)
    private LocalDate dataVenda;

    @NotNull
    @Column(name = "datacriacao", nullable = false)
    private LocalDate mesCriacao;

    @Column(name = "dataatualizacao")
    private LocalDate mesAtualizacao;

    public Venda() {
    }
}
