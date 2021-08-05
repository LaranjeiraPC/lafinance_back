package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.model.CompraVenda;

public interface CompraVendaService {
	void salvarRegistro(List<CompraVenda> compraVenda);
	List<CompraVenda> consultarCompraVendaPeloIdVenda(Integer id);
	List<CompraVenda> consultarCompraVendaPeloIdCompra(Integer id);
}
