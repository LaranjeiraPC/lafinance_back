package com.lafinance.dashboard.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "investimento")
public class Investimento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1030098088191270895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "acao", nullable = false)
	private Acao acao;
		
	@ManyToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="ultimovalorcompra", nullable=false)
	private BigDecimal ultimoValorCompra;
	
	@Column(name="totalcompra", nullable=false)
	private BigDecimal totalCompra;
	
	@Column(name="ultimadataatualizacao", nullable=false)
	private LocalDate ultimaDataAtualizacao;
	
	@Column(name="ultimadatavenda", nullable=false)
	private LocalDate ultimaDataVenda;

	@Column(name="ultimadatacompra", nullable=false)
	private LocalDate ultimaDataCompra;
	
	public Investimento() {}

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
}
