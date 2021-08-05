package com.lafinance.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.model.Venda;

public class VendaDTO {

	private Integer id;
	private Ativo ativo;
	private Integer quantidade;
	private BigDecimal valorBrutoVenda;
	private BigDecimal valorAtivoVenda;
	private LocalDate dataVenda;
	private LocalDate mesCriacao;
	private LocalDate mesAtualizacao;

	public VendaDTO() {
	}

	public VendaDTO(Venda venda) {
		setId(venda.getId());
		setAtivo(venda.getAtivo());
		setQuantidade(venda.getQuantidade());
		setValorBrutoVenda(venda.getValorBrutoVenda());
		setValorAtivoVenda(venda.getValorAtivoVenda());
		setDataVenda(venda.getDataVenda());
		setMesCriacao(venda.getMesCriacao());
		setMesAtualizacao(venda.getMesAtualizacao());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorBrutoVenda() {
		return valorBrutoVenda;
	}

	public void setValorBrutoVenda(BigDecimal valorBrutoVenda) {
		this.valorBrutoVenda = valorBrutoVenda;
	}

	public BigDecimal getValorAtivoVenda() {
		return valorAtivoVenda;
	}

	public void setValorAtivoVenda(BigDecimal valorAtivoVenda) {
		this.valorAtivoVenda = valorAtivoVenda;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public LocalDate getMesCriacao() {
		return mesCriacao;
	}

	public void setMesCriacao(LocalDate mesCriacao) {
		this.mesCriacao = mesCriacao;
	}

	public LocalDate getMesAtualizacao() {
		return mesAtualizacao;
	}

	public void setMesAtualizacao(LocalDate mesAtualizacao) {
		this.mesAtualizacao = mesAtualizacao;
	}

}
