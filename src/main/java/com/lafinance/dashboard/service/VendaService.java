package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.util.Response;

public interface VendaService {
	Response salvarVenda(Object[] dados);
	List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes);
	VendaDTO consultarDetalhesVenda(String id);	
	List<VendaDTO> consultarRelatorioVenda(String ano, String mes);
}
