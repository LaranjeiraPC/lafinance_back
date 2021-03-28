package com.lafinance.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;

public class VendaDTO {

	private Integer id;
	private Integer quantidade;
	private BigDecimal totalVenda;
	private BigDecimal valorVenda;
	private LocalDate dataVenda;
	private BigDecimal lucroBruto;
	private String acao;
	private Investimento investimento;
	private Compra compra;
	private String usuario;

	private Integer idInvestimento;

	public VendaDTO() {
	}

	public VendaDTO(String acao, Integer quantidade, BigDecimal valor, Integer investimento) {
		this.acao = acao;
		this.quantidade = quantidade;
		this.valorVenda = valor;
		this.idInvestimento = investimento;
	}

	public VendaDTO(Integer id, String acao, Integer quantidade, BigDecimal totalVenda, BigDecimal valor,
			BigDecimal lucroBruto, LocalDate dataVenda, Compra compra, String usuario) {
		this.id = id;
		this.acao = acao;
		this.quantidade = quantidade;
		this.totalVenda = totalVenda;
		this.valorVenda = valor;
		this.lucroBruto = lucroBruto;
		this.dataVenda = dataVenda;
		this.compra = compra;
		this.usuario = usuario;
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

	public BigDecimal getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(BigDecimal totalVenda) {
		this.totalVenda = totalVenda;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getLucroBruto() {
		return lucroBruto;
	}

	public void setLucroBruto(BigDecimal lucroBruto) {
		this.lucroBruto = lucroBruto;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Integer getIdInvestimento() {
		return idInvestimento;
	}

	public void setIdInvestimento(Integer idInvestimento) {
		this.idInvestimento = idInvestimento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
