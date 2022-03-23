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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nomeUsuario;

    @NotNull
    @NotBlank
    @Column(name = "senha", nullable = false)
    private String senhaUsuario;

    @NotNull
    @NotBlank
    @Column(name = "email", nullable = false)
    private String emailUsuario;

    @NotNull
    @NotBlank
    @Column(name = "perfil", nullable = false)
    private String perfilUsuario;

    @NotNull
    @NotBlank
    @Column(name = "ativo", nullable = false)
    private String ativoUsuario;

    public Usuario() {
    }

    public Usuario(Usuario user) {
        this.id = user.getId();
        this.nomeUsuario = user.getNomeUsuario();
        this.emailUsuario = user.getEmailUsuario();
        this.perfilUsuario = user.getPerfilUsuario();
        this.ativoUsuario = user.getAtivoUsuario();
    }

}
