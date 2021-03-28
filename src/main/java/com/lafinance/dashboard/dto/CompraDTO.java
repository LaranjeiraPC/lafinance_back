package com.lafinance.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;

public class CompraDTO {

	private Integer id;
	private Integer quantidade;
	private String acao;
	private BigDecimal totalCompra;
	private BigDecimal valorCompra;
	private LocalDate dataCompra;	
	private String indicadorAtivo;	
	private Investimento investimento;
	
	public CompraDTO(Compra compra) {
		this.id = compra.getId();
		this.quantidade = compra.getQuantidade();
		this.acao = compra.getInvestimento().getAcao().getNomeAcao();
		this.valorCompra = compra.getValorCompra();
		this.totalCompra = compra.getTotalCompra();
		this.indicadorAtivo = compra.getIndicadorAtivo();
		this.dataCompra = compra.getDataCompra();	
		this.investimento = compra.getInvestimento();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public BigDecimal getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}
	public BigDecimal getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getIndicadorAtivo() {
		return indicadorAtivo;
	}
	public void setIndicadorAtivo(String indicadorAtivo) {
		this.indicadorAtivo = indicadorAtivo;
	}
	public Investimento getInvestimento() {
		return investimento;
	}
	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}
	
}
