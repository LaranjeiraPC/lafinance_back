package com.lafinance.dashboard.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compravenda")
public class CompraVenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -760598687272966313L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "compra", nullable = false)
	private Acao compra;
	
	@ManyToOne
	@JoinColumn(name = "venda", nullable = false)
	private Venda venda;
	
	public CompraVenda() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Acao getCompra() {
		return compra;
	}

	public void setCompra(Acao compra) {
		this.compra = compra;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}
