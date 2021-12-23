package com.lafinance.dashboard.util;

import java.util.List;

public class Response<K> {
	
	private String mensagem;
	private TipoResponse tipo;
	private List<K> dtos;

	public enum TipoResponse {
		SUCESSO, ERRO, ATUALIZACAO, VISUALIZACAO
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TipoResponse getTipo() {
		return tipo;
	}

	public void setTipo(TipoResponse tipo) {
		this.tipo = tipo;
	}

	public List<K> getDtos() {
		return dtos;
	}

	public void setDtos(List<K> dtos) {
		this.dtos = dtos;
	}
}
