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
@Table(name = "venda")
public class Venda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3574568785078311013L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ativo", nullable = false)
	private Ativo ativo;
	 
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="valorbrutovenda", nullable=false)
	private BigDecimal valorBrutoVenda;
	
	@Column(name="valorativovenda", nullable=false)
	private BigDecimal valorAtivoVenda;
	
	@Column(name="datavenda", nullable=false)
	private LocalDate dataVenda;
	
	@Column(name="datacriacao", nullable=false)
	private LocalDate mesCriacao;
	
	@Column(name="dataatualizacao", nullable=true)
	private LocalDate mesAtualizacao;
		
	public Venda() {}

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
