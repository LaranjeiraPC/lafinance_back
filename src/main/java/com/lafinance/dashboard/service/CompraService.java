package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.CompraDTO;
import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;

public interface CompraService {
	
	List<CompraDTO> consultarCompras(Integer investimento);
	
	void salvarCompra(Object[] objeto, Investimento investimento);
	
	List<CompraDTO> consultarInvestimento(Investimento investimento);
	
	Compra consultarCompra(Integer id);
	
	void atualizarCompra(Compra compra);
}
