package com.lafinance.dashboard.dto;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Usuario;

public class AcaoDTO {

	private Integer id;
	private String nomeAcao;
	private String ativoAcao;
	private Usuario usuario;
	
	public AcaoDTO() {}
	
	public AcaoDTO(Acao acao) {
		setId(acao.getId());
		setNomeAcao(acao.getNomeAcao());
		setAtivoAcao(acao.getAtivoAcao());
		setUsuario(acao.getUsuario());
	}
	
	public AcaoDTO(String acao) {
		setNomeAcao(acao);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public String getNomeAcao() {
		return nomeAcao;
	}

	public void setNomeAcao(String nomeAcao) {
		this.nomeAcao = nomeAcao;
	}

	
}
