package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.util.Response;

public interface InvestimentoService {
	Response salvarCompra(Object[] acao);
	List<Investimento> consultarInvestimento(String usuario);
}
