package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.domain.dto.AtivoDTO;
import com.lafinance.dashboard.domain.model.Ativo;
import com.lafinance.dashboard.util.Response;

public interface AtivoService {
	AtivoDTO salvarAtivo(AtivoDTO ativoDTO) throws Exception;
	AtivoDTO editarAtivo(AtivoDTO ativoDTO) throws Exception;
	void excluirAtivo(Integer id) throws Exception;
	AtivoDTO consultarNomeAtivo(String nome) throws Exception;
	List<AtivoDTO> listarAtivo();
}
