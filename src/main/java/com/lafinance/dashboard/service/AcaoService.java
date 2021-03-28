package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.util.Response;

public interface AcaoService {
	
	List<Acao> carregarAcoes(String usuario);
	
	List<AcaoDTO> carregarAcoesNome(String usuario);
	
	Response salvarAcao(Object[] acao);
	
	Response editarAcao(Object[] acao);
	
	Response excluirAcao(Object[] acao);
	
	Acao consultarAcaoPeloId(Integer id);
	
	Acao consultarAcaoNome(String nome);
}
