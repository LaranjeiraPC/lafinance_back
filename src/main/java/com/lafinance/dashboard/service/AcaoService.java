package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.util.List;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.util.Response;

public interface AcaoService {

	List<AcaoDTO> consultarAcoesAtivos();
	Response cadastrarAcao(Acao acao);
	Response excluirAcao(Integer id);
	Response editarAcao(AcaoDTO acao);

	void inativarAcoes(List<Acao> acoes);
	void ativarAcoes(List<Acao> acoes);
	List<Acao> consultarAcoesId(List<Integer> id);
	BigDecimal calcularLucroBruto(List<Integer> idsCompra);

}
