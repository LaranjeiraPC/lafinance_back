package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lafinance.dashboard.domain.dto.AcaoDTO;
import com.lafinance.dashboard.domain.model.Acao;

public interface AcaoService {

	AcaoDTO cadastrarAcao(AcaoDTO acaoDTO) throws Exception;
	void editarAcao(AcaoDTO acao) throws Exception;
	void excluirAcao(Integer id) throws Exception;
	List<AcaoDTO> listarAcoesAtivosVenda(String nome) throws Exception;
	List<AcaoDTO> inativarAcoes(List<AcaoDTO> acaoDTO);
	List<AcaoDTO> listarAcoesAtivosMesCorrente(Integer mes, Integer ano) throws Exception;
	List<AcaoDTO> listarAcoesAtivosOutrosMeses(List<Acao> ids) throws Exception;
	List<AcaoDTO> listarAcoesAtivos() throws Exception;

	List<Acao> consultarAcoesPeloIdVenda(Integer idVenda) throws Exception;
	void ativarAcoes(List<Acao> acoes) throws Exception;
	BigDecimal consultarInvestimentoTotal();
	Integer consultaQuantidadeCota();
	List<String> listarAtivosAgrupandoByNomeAtivo();
	void atualizarUltimaCotacao(List<Map<String, BigDecimal>> cotacoes);

}
