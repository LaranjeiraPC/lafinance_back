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
		setNomecao(acao.getNomeAcao());
		setAtivoAcao(acao.getAtivoAcao());
		setUsuario(acao.getUsuario());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeAcao() {
		return nomeAcao;
	}
	public void setNomecao(String nomeAcao) {
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
