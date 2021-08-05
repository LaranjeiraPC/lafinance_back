package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.util.Response;

public interface AcaoService {
	Response salvarCompra(Object[] dados);
	List<AcaoDTO> consultarAcoesPeloAnoMesSelecionado(String ano, String mes);
	List<AcaoDTO> consultarAcoesAtivos(String mes);
	List<String> consultarAno();
	List<String> consultarMes(String ano);
	AcaoDTO consultarAcaoVenda(String id);
	List<AcaoDTO> consultarAcoesVenda(String id);
	List<Acao> consultarAcoesIDs(List<Integer> ids);
	void inativarAcoes(List<Acao> acoes);
	List<AcaoDTO> consultarAcoesAtivo(String ativo);
	List<Acao> findByStatus(String status);
}
