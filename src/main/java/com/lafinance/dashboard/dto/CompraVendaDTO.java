package com.lafinance.dashboard.dto;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.model.Venda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraVendaDTO {

	private Integer id;
	private Acao compra;
	private Venda venda;
	
	public CompraVendaDTO (CompraVenda compraVenda) {
		setId(compraVenda.getId());
		setCompra(compraVenda.getCompra());
		setVenda(compraVenda.getVenda());
	}
}
