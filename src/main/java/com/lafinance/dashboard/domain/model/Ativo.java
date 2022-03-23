package com.lafinance.dashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "ativo")
public class Ativo implements Serializable {

    private static final long serialVersionUID = 6649075861060230218L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;

    public Ativo() {
    }

}
