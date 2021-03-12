package com.lafinance.dashboard.dto;

import java.io.Serializable;

import com.lafinance.dashboard.model.Usuario;

public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8448773733183026907L;
	private Integer id;
	private String nomeUsuario;
	private String emailUsuario;
	private String perfilUsuario;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario user) {
		this.id = user.getId();
		this.nomeUsuario = user.getNomeUsuario();
		this.perfilUsuario = user.getPerfilUsuario();
		this.emailUsuario = user.getEmailUsuario();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
}
