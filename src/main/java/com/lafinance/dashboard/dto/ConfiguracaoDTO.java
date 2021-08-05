package com.lafinance.dashboard.dto;

import java.math.BigDecimal;

import com.lafinance.dashboard.model.Configuracao;

public class ConfiguracaoDTO {
	
	private Integer id;
	private BigDecimal valorBrutoMeta;
	
	public ConfiguracaoDTO() {}
	
	public ConfiguracaoDTO(Configuracao config) {
		setId(config.getId());
		setValorBrutoMeta(config.getValorBrutoMeta());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getValorBrutoMeta() {
		return valorBrutoMeta;
	}
	public void setValorBrutoMeta(BigDecimal valorBrutoMeta) {
		this.valorBrutoMeta = valorBrutoMeta;
	}

}
