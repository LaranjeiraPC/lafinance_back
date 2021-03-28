package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.InvestimentoDTO;
import com.lafinance.dashboard.util.Response;

public interface InvestimentoService {
	
	Response salvarInvestimento(Object[] acao);
	
	List<InvestimentoDTO> consultarInvestimento(String usuario);
}
