package com.lafinance.dashboard.domain.dto;

import com.lafinance.dashboard.domain.model.Ativo;
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
