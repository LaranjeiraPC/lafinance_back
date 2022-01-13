package com.lafinance.dashboard.dto;

import java.io.Serializable;

import com.lafinance.dashboard.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8448773733183026907L;
	private Integer id;
	private String nomeUsuario;
	private String emailUsuario;
	private String perfilUsuario;
	private String ativoUsuario;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario user) {
		this.id = user.getId();
		this.nomeUsuario = user.getNomeUsuario();
		this.perfilUsuario = user.getPerfilUsuario();
		this.emailUsuario = user.getEmailUsuario();
		this.ativoUsuario = user.getAtivoUsuario();
	}

}
