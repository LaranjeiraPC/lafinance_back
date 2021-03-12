package com.lafinance.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acao")
public class Acao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1559264000581873246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nome", nullable=false)
	private String nomeAcao;
	
	@Column(name="ativo", nullable=false)
	private String ativoAcao;
	
	@ManyToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;
	
	public Acao() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAcao() {
		return nomeAcao;
	}

	public void setNomeAcao(String nomeAcao) {
		this.nomeAcao = nomeAcao;
	}

	public String getAtivoAcao() {
		return ativoAcao;
	}

	public void setAtivoAcao(String ativoAcao) {
		this.ativoAcao = ativoAcao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
