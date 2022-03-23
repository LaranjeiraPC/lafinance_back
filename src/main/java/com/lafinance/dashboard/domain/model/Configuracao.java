package com.lafinance.dashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "configuracao")
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 430605114124788253L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "valorbrutometa")
    private BigDecimal valorBrutoMeta;

    @Column(name = "ativoum")
    private String ativoUm;

    @Column(name = "ativodois")
    private String ativoDois;

    public Configuracao() {
    }

}
