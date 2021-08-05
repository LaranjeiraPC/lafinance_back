package com.lafinance.dashboard.dto;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.model.Venda;

public class CompraVendaDTO {

	private Integer id;
	private Acao compra;
	private Venda venda;
	
	public CompraVendaDTO (CompraVenda compraVenda) {
		setId(compraVenda.getId());
		setCompra(compraVenda.getCompra());
		setVenda(compraVenda.getVenda());
	}
	
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
