package com.lafinance.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Ativo;

public class AcaoDTO {

	private Integer id;
	private Ativo ativo;
	private Integer quantidade;
	private BigDecimal valorBrutoPago;
	private BigDecimal valorAtivoPago;
	private LocalDate dataCompra;
	private LocalDate mesCriacao;
	private LocalDate mesAtualizacao;
	private String status;
	
	public AcaoDTO() {}
	
	public AcaoDTO(Acao acao) {
		setId(acao.getId());
		setAtivo(acao.getAtivo());
		setQuantidade(acao.getQuantidade());
		setValorBrutoPago(acao.getValorBrutoPago());
		setValorAtivoPago(acao.getValorAtivoPago());
		setDataCompra(acao.getDataCompra());
		setMesCriacao(acao.getMesCriacao());
		setMesAtualizacao(acao.getMesAtualizacao());
		setStatus(acao.getStatus());
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

	public BigDecimal getValorBrutoPago() {
		return valorBrutoPago;
	}

	public void setValorBrutoPago(BigDecimal valorBrutoPago) {
		this.valorBrutoPago = valorBrutoPago;
	}

	public BigDecimal getValorAtivoPago() {
		return valorAtivoPago;
	}

	public void setValorAtivoPago(BigDecimal valorAtivoPago) {
		this.valorAtivoPago = valorAtivoPago;
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

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
