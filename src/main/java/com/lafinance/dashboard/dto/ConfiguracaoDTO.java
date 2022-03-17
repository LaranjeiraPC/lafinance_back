package com.lafinance.dashboard.dto;

import java.math.BigDecimal;

import com.lafinance.dashboard.model.Configuracao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfiguracaoDTO {
	
	private Integer id;
	private BigDecimal valorBrutoMeta;
	private String ativoUm;
	private String ativoDois;
	
	public ConfiguracaoDTO() {}
	
	public ConfiguracaoDTO(Configuracao config) {
		setId(config.getId());
		setValorBrutoMeta(config.getValorBrutoMeta());
		setAtivoUm(config.getAtivoUm());
		setAtivoDois(config.getAtivoDois());
	}

}
