package com.lafinance.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.model.Usuario;
import com.lafinance.dashboard.model.Venda;

public class InvestimentoDTO {

	private Integer id;
	private Acao acao;
	private Usuario usuario;
	private Integer quantidade;
	private BigDecimal ultimoValorCompra;
	private BigDecimal totalCompra;
	private LocalDate ultimaDataAtualizacao;
	private LocalDate ultimaDataVenda;
	private LocalDate ultimaDataCompra;
	private String nomeAcao;
	private List<Venda> venda;
	private  List<Compra> compra;
	
	public InvestimentoDTO() {}
	
	public InvestimentoDTO(Investimento investimento) {
		this.id = investimento.getId();
		this.nomeAcao = investimento.getNomeAcao();
		this.quantidade = investimento.getQuantidade();
		this.ultimoValorCompra = investimento.getUltimoValorCompra();
	}
	
	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(List<Venda> venda) {
		this.venda = venda;
	}

	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getUltimoValorCompra() {
		return ultimoValorCompra;
	}
	public void setUltimoValorCompra(BigDecimal ultimoValorCompra) {
		this.ultimoValorCompra = ultimoValorCompra;
	}
	public BigDecimal getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}
	public LocalDate getUltimaDataAtualizacao() {
		return ultimaDataAtualizacao;
	}
	public void setUltimaDataAtualizacao(LocalDate ultimaDataAtualizacao) {
		this.ultimaDataAtualizacao = ultimaDataAtualizacao;
	}
	public LocalDate getUltimaDataVenda() {
		return ultimaDataVenda;
	}
	public void setUltimaDataVenda(LocalDate ultimaDataVenda) {
		this.ultimaDataVenda = ultimaDataVenda;
	}
	public LocalDate getUltimaDataCompra() {
		return ultimaDataCompra;
	}
	public void setUltimaDataCompra(LocalDate ultimaDataCompra) {
		this.ultimaDataCompra = ultimaDataCompra;
	}
	public String getNomeAcao() {
		return nomeAcao;
	}
	public void setNomeAcao(String nomeAcao) {
		this.nomeAcao = nomeAcao;
	}
	
}
