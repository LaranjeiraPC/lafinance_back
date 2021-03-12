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
@Table(name = "compra")
public class Compra implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1912456044540887407L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="totalcompra", nullable=false)
	private BigDecimal totalCompra;
	
	@Column(name="valorcompra", nullable=false)
	private BigDecimal valorCompra;
	
	@Column(name="datacompra", nullable=false)
	private LocalDate dataCompra;	
	
	@Column(name="indicadorativo", nullable=false)
	private String indicadorAtivo;	
	
	@ManyToOne
	@JoinColumn(name = "investimento", nullable = false)
	private Investimento investimento;
	
	public Compra() {}

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
