package com.lafinance.dashboard.util;

public class Response {
	
	private String mensagem;
	private TipoResponse tipo;

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
	
}
