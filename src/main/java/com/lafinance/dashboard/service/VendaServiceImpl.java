package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.model.Venda;
import com.lafinance.dashboard.repository.VendaRepository;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class VendaServiceImpl implements VendaService {

	private final Logger log = LoggerFactory.getLogger(VendaServiceImpl.class);

	private final VendaRepository repository;
	private final AcaoService acaoService;
	private final AtivoService ativoService;
	private final CompraVendaService compraVendaService;
	
	private Venda venda = new Venda();

	public VendaServiceImpl(VendaRepository repository, AtivoService ativoService, AcaoService acaoService,
			CompraVendaService compraVenda) {
		this.repository = repository;
		this.ativoService = ativoService;
		this.acaoService = acaoService;
		this.compraVendaService = compraVenda;
	}

	@Override
	public Response salvarVenda(Object[] dados) {
		Response response = new Response();
		BigDecimal valorBrutoVenda = new BigDecimal((String) dados[3]);
		BigDecimal valorAtivoVenda = new BigDecimal((String) dados[4]);

		try {
			venda = new Venda();
			log.debug("Preparando entidade Venda");

			List<CompraVenda> compraVendaList = new ArrayList<>();
			
			List<String> acoesIds = (List<String>) dados[0];
			List<Integer> acoesIdsInteger = new ArrayList<>();
			acoesIds.forEach(a -> acoesIdsInteger.add(Integer.parseInt(a)));

			Ativo ativo = ativoService.consultarNomeAtivo((String) dados[1]);
			List<Acao> acoes = acaoService.consultarAcoesIDs(acoesIdsInteger);
			acaoService.inativarAcoes(acoes);
			
			venda.setAtivo(ativo);
			venda.setQuantidade(Integer.parseInt((String) dados[2]));
			venda.setValorAtivoVenda(valorAtivoVenda);
			venda.setValorBrutoVenda(valorBrutoVenda);
			venda.setDataVenda(Util.montarData((String) dados[5]));
			venda.setMesCriacao(LocalDate.now());

			venda = repository.save(venda);
			
			acoes.forEach(a -> {
				CompraVenda compraVenda = new CompraVenda();
				compraVenda.setVenda(venda);
				compraVenda.setCompra(a);
				compraVendaList.add(compraVenda);
			});
			compraVendaService.salvarRegistro(compraVendaList);

			log.debug("Entidade Venda aramazenado");
			response.setTipo(TipoResponse.SUCESSO);
			return response;
		} catch (Exception e) {
			log.debug("Erro ao armazenar entidade Venda");
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao salvar registro!");
			return response;
		}
	}

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes) {
		log.debug("Consultar vendas pelo ano e mês");
		List<VendaDTO> dtoList = new ArrayList<>();
		repository.findByDataVenda(Integer.parseInt(ano),
				Integer.parseInt(Util.converterNomeMesParaInteiro(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
		return dtoList;
	}

	@Override
	public VendaDTO consultarDetalhesVenda(String id) {
		log.debug("Consultar detalhes da venda pelo id");
		return new VendaDTO(repository.getOne(Integer.parseInt(id)));
	}

}
