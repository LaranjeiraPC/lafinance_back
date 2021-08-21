package com.lafinance.dashboard.dto;

import java.math.BigDecimal;

import com.lafinance.dashboard.model.Configuracao;

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

	public String getAtivoUm() {
		return ativoUm;
	}

	public void setAtivoUm(String ativoUm) {
		this.ativoUm = ativoUm;
	}

	public String getAtivoDois() {
		return ativoDois;
	}

	public void setAtivoDois(String ativoDois) {
		this.ativoDois = ativoDois;
	}

}
