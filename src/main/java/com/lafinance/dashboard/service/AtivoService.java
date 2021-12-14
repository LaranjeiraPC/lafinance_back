package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.AtivoDTO;
import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.util.Response;

public interface AtivoService {
	Response salvarAtivo(Ativo ativo);
	Response editarAtivo(Ativo ativo);
	Response excluirAtivo(Integer id);
	Ativo consultarNomeAtivo(String nome);
	List<AtivoDTO> consultarAtivo();
}
