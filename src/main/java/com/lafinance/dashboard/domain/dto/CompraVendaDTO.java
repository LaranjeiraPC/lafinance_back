package com.lafinance.dashboard.domain.dto;

import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.domain.model.CompraVenda;
import com.lafinance.dashboard.domain.model.Venda;
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
