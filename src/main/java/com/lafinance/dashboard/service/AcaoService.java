package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.util.List;

import com.lafinance.dashboard.domain.dto.AcaoDTO;
import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.util.Response;

public interface AcaoService {

	List<AcaoDTO> consultarAcoesAtivosOutrosMeses(List<Acao> ids) throws Exception;
	Response cadastrarAcao(Acao acao) throws Exception;
	Response excluirAcao(Integer id);
	Response editarAcao(AcaoDTO acao) throws Exception;
	List<AcaoDTO> consultarAcoesAtivosVenda(String nome);
	List<Acao> consultarAcoesPeloIdVenda(Integer idVenda);

	Response inativarAcoes(List<Acao> acoes);
	void ativarAcoes(List<Acao> acoes);
	List<Acao> consultarAcoesId(List<Integer> id);
	BigDecimal calcularLucroBruto(List<Integer> idsCompra);

	Response atualizarPrecoAtual();
	List<Acao> consultarAcoesAtivosMesCorrente(Integer mes, Integer ano);
	List<AcaoDTO> consultarAcoesAtivos();


}
