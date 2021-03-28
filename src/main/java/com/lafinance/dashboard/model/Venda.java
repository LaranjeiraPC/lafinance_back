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
import javax.persistence.Transient;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 720249669890146068L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "totalvenda", nullable = false)
	private BigDecimal totalVenda;

	@Column(name = "valorvenda", nullable = false)
	private BigDecimal valorVenda;

	@Column(name = "datavenda", nullable = false)
	private LocalDate dataVenda;

	@Column(name = "lucrobruto", nullable = false)
	private BigDecimal lucroBruto;

	@ManyToOne
	@JoinColumn(name = "investimento", nullable = false)
	private Investimento investimento;

	@ManyToOne
	@JoinColumn(name = "compra", nullable = false)
	private Compra compra;

	@Transient
	private String acao;

	@Transient
	private Integer idInvestimento;

	@Transient
	private String usuario;
	
	public Venda() {
	}

	public Venda(String acao, Integer quantidade, BigDecimal valor, Integer investimento) {
		this.acao = acao;
		this.quantidade = quantidade;
		this.valorVenda = valor;
		this.idInvestimento = investimento;
	}

	public Venda(Integer id, String acao, Integer quantidade, BigDecimal totalVenda, BigDecimal valor,
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

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Integer getIdInvestimento() {
		return idInvestimento;
	}

	public void setIdInvestimento(Integer idInvestimento) {
		this.idInvestimento = idInvestimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
