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
@Table(name = "acao")
public class Acao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1559264000581873246L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ativo", nullable = false)
	private Ativo ativo;
	 
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="valorbrutopago", nullable=false)
	private BigDecimal valorBrutoPago;
	
	@Column(name="valorativopago", nullable=false)
	private BigDecimal valorAtivoPago;
	
	@Column(name="datacompra", nullable=false)
	private LocalDate dataCompra;
	
	@Column(name="datacriacao", nullable=false)
	private LocalDate mesCriacao;
	
	@Column(name="dataatualizacao", nullable=true)
	private LocalDate mesAtualizacao;
	
	@Column(name="status", nullable=true)
	private String status;
	
	public Acao() {}

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