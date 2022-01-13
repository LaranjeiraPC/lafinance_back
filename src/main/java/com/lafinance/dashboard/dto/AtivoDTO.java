package com.lafinance.dashboard.dto;

import com.lafinance.dashboard.model.Ativo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
