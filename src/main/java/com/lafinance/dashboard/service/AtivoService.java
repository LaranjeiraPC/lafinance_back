package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.AtivoDTO;
import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.util.Response;

public interface AtivoService {
	Response salvarAtivo(Object[] dados);
	Response editarAtivo(Object[] dados);
	Response excluirAtivo(String id);
	Ativo consultarNomeAtivo(String nome);
	List<AtivoDTO> consultarAtivo();
}
