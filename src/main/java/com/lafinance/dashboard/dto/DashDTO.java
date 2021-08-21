package com.lafinance.dashboard.dto;

import java.math.BigDecimal;

public class DashDTO {
	
	private Integer quantidadeTotal;
	private BigDecimal valorBrutoTotal;
	private BigDecimal valorInvestimentoTotal;
	private BigDecimal valorBrutoMeta;
	private String ativoUm;
	private String ativoDois;
	
	public DashDTO () {}
	
	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public BigDecimal getValorBrutoTotal() {
		return valorBrutoTotal;
	}
	public void setValorBrutoTotal(BigDecimal valorBrutoTotal) {
		this.valorBrutoTotal = valorBrutoTotal;
	}
	public BigDecimal getValorInvestimentoTotal() {
		return valorInvestimentoTotal;
	}
	public void setValorInvestimentoTotal(BigDecimal valorInvestimentoTotal) {
		this.valorInvestimentoTotal = valorInvestimentoTotal;
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
