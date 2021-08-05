package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

	private final Logger log = LoggerFactory.getLogger(AcaoServiceImpl.class);

	private final AcaoRepository repository;
	private final AtivoService ativoService;

	public AcaoServiceImpl(AcaoRepository repository, AtivoService ativoService) {
		this.repository = repository;
		this.ativoService = ativoService;
	}

	@Override
	public Response salvarCompra(Object[] dados) {
		Response response = new Response();
		BigDecimal valorBrutoPago = new BigDecimal((String) dados[2]);
		BigDecimal valorAtivoPago = new BigDecimal((String) dados[3]);

		try {
			log.debug("Preparando entidade ACAO");

			Ativo ativo = ativoService.consultarNomeAtivo((String) dados[0]);
			Acao acao = new Acao();
			acao.setAtivo(ativo);
			acao.setDataCompra(Util.montarData((String) dados[4]));
			acao.setQuantidade(Integer.parseInt((String) dados[1]));
			acao.setValorAtivoPago(valorAtivoPago);
			acao.setValorBrutoPago(valorBrutoPago);
			acao.setMesCriacao(LocalDate.now());
			acao.setStatus("S");

			repository.save(acao);

			log.debug("Entidade ACAO aramazenado");
			response.setTipo(TipoResponse.SUCESSO);
			return response;
		} catch (Exception e) {
			log.debug("Erro ao armazenar entidade ACAO");
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao salvar registro!");
			return response;
		}
	}

	@Override
	public List<AcaoDTO> consultarAcoesPeloAnoMesSelecionado(String ano, String mes) {
		List<AcaoDTO> dtoList = new ArrayList<>();
		List<Acao> acao = repository.findByDataCompraAndStatus(Integer.parseInt(ano),
				Integer.parseInt(Util.converterNomeMesParaInteiro(mes)));
		acao.forEach(a -> dtoList.add(new AcaoDTO(a)));
		return dtoList;
	}

	@Override
	public List<String> consultarAno() {
		List<Acao> acao = repository.findByStatus("S");
		List<String> anos = new ArrayList<>();
		acao.forEach(a -> anos.add(Util.montarAno(a.getDataCompra()).toString()));
		return anos;
	}

	@Override
	public List<String> consultarMes(String ano) {
		List<Acao> acao = repository.consultarMeses(Integer.parseInt(ano));
		List<String> meses = new ArrayList<>();
		acao.forEach(a -> meses.add(Util.montarMes(a.getDataCompra())));
		return meses;
	}

	@Override
	public List<AcaoDTO> consultarAcoesAtivos(String mes) {
		List<AcaoDTO> dtoList = new ArrayList<>();
		List<Acao> acao = repository.findByAllAndStatus(Integer.parseInt(Util.converterNomeMesParaInteiro(mes)));
		acao.forEach(a -> dtoList.add(new AcaoDTO(a)));
		return dtoList;
	}

	@Override
	public AcaoDTO consultarAcaoVenda(String id) {
		Acao acao = repository.getOne(Integer.parseInt(id));
		return new AcaoDTO(acao);
	}

	@Override
	public List<AcaoDTO> consultarAcoesVenda(String id) {
		List<AcaoDTO> dtoList = new ArrayList<>();
		Acao acao = repository.getOne(Integer.parseInt(id));
		List<Acao> acoes = repository.findByAtivoNomeAndStatus(acao.getAtivo().getNome(), "S");
		acoes.forEach(a -> dtoList.add(new AcaoDTO(a)));
		return dtoList;
	}

	@Override
	public List<Acao> consultarAcoesIDs(List<Integer> ids) {
		List<Acao> acoes = new ArrayList<>();
		ids.forEach(id -> acoes.add(repository.getOne(id)));
		return acoes;
	}

	@Override
	public void inativarAcoes(List<Acao> acoes) {
		acoes.forEach(a -> {
			Acao acao = repository.getOne(a.getId());
			acao.setStatus("N");
		});
				
	}

	@Override
	public List<AcaoDTO> consultarAcoesAtivo(String ativo) {
		List<AcaoDTO> dtoList = new ArrayList<>();
		List<Acao> acoes = repository.findByAtivoNomeAndStatus(ativo, "S");
		acoes.forEach(a -> dtoList.add(new AcaoDTO(a)));
		return dtoList;
	}

	@Override
	public List<Acao> findByStatus(String status) {
		return repository.findByStatus(status);
	}

}
