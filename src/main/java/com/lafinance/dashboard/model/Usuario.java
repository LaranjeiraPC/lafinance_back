package com.lafinance.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nome", nullable=false)
	private String nomeUsuario;
	
	@Column(name="senha", nullable=false)
	private String senhaUsuario;
	
	@Column(name="email", nullable=false)
	private String emailUsuario;
	
	@Column(name="perfil", nullable=false)
	private String perfilUsuario;
	
	@Column(name="ativo", nullable=false)
	private String ativoUsuario;
	
	public Usuario() {}
	
	public Usuario(Usuario user) {
		this.id = user.getId();
		this.nomeUsuario = user.getNomeUsuario();
		this.emailUsuario = user.getEmailUsuario();
		this.perfilUsuario = user.getPerfilUsuario();
		this.ativoUsuario = user.getAtivoUsuario();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAtivoUsuario() {
		return ativoUsuario;
	}

	public void setAtivoUsuario(String ativoUsuario) {
		this.ativoUsuario = ativoUsuario;
	}
	
}
