package com.lafinance.dashboard.dto;

import com.lafinance.dashboard.model.Ativo;

public class AtivoDTO {

	private Integer id;
	private String nome;
	private String status;

	public AtivoDTO() {
	}

	public AtivoDTO(Ativo ativo) {
		setId(ativo.getId());
		setNome(ativo.getNome());
		setStatus(ativo.getStatus());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
