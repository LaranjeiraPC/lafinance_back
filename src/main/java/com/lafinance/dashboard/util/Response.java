package com.lafinance.dashboard.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response<K> {
	
	private String mensagem;
	private TipoResponse tipo;
	private List<K> dtos;

	public Response(){}

	public Response(String mensagem, TipoResponse tipo, List<K> dtos){
		this.mensagem = mensagem;
		this.tipo = tipo;
		this.dtos = dtos;
	}

	public enum TipoResponse {
		SUCESSO, ERRO, ATUALIZACAO, VISUALIZACAO
	}
}
