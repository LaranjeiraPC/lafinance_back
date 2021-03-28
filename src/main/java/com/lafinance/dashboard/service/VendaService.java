package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.util.Response;

public interface VendaService {
	
	List<VendaDTO> consultarVendas(String investimento);
	
	Response salvarVenda(Object[] acao);
	
	Response editarVenda(Object[] acao);
	
	Response excluirVenda(Object[] acao);
	
	List<VendaDTO> consultarVenda(Integer id);
	
}
