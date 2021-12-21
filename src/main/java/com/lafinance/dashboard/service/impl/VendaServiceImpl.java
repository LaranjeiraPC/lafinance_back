package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.service.CompraVendaService;
import com.lafinance.dashboard.service.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.VendaDTO;
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

//	@Override
//	public Response salvarVenda(Object[] dados) {
//		Response response = new Response();
//		BigDecimal valorBrutoVenda = new BigDecimal((String) dados[3]);
//		BigDecimal valorAtivoVenda = new BigDecimal((String) dados[4]);
//
//		try {
//			venda = new Venda();
//			log.debug("Preparando entidade Venda");
//
//			List<CompraVenda> compraVendaList = new ArrayList<>();
//
//			List<String> acoesIds = (List<String>) dados[0];
//			List<Integer> acoesIdsInteger = new ArrayList<>();
//			acoesIds.forEach(a -> acoesIdsInteger.add(Integer.parseInt(a)));
//
//			Ativo ativo = ativoService.consultarNomeAtivo((String) dados[1]);
//			List<Acao> acoes = acaoService.consultarAcoesId(acoesIdsInteger);
//			acaoService.inativarAcoes(acoes);
//
//			venda.setAtivo(ativo);
//			venda.setQuantidade(Integer.parseInt((String) dados[2]));
//			venda.setValorAtivoVenda(valorAtivoVenda);
//			venda.setValorBrutoVenda(valorBrutoVenda);
//			venda.setDataVenda(Util.montarData((String) dados[5]));
//			venda.setMesCriacao(LocalDate.now());
//
//			venda = repository.save(venda);
//
//			acoes.forEach(a -> {
//				CompraVenda compraVenda = new CompraVenda();
//				compraVenda.setVenda(venda);
//				compraVenda.setCompra(a);
//				compraVendaList.add(compraVenda);
//			});
//			compraVendaService.salvarRegistro(compraVendaList);
//
//			log.debug("Entidade Venda aramazenado");
//			response.setTipo(TipoResponse.SUCESSO);
//			return response;
//		} catch (Exception e) {
//			log.debug("Erro ao armazenar entidade Venda");
//			response.setTipo(TipoResponse.ERRO);
//			response.setMensagem("Erro ao salvar registro!");
//			return response;
//		}
//	}

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes) {
		log.debug("Consultar vendas pelo ano e mês");
		List<VendaDTO> dtoList = new ArrayList<>();
		repository.findByDataVenda(Integer.parseInt(ano),
				Integer.parseInt(Util.converterNomeMesParaInteiro(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
		return dtoList;
	}

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionadoInteiro(String ano, String mes) {
		log.debug("Consultar vendas pelo ano e mês");
		List<VendaDTO> dtoList = new ArrayList<>();
		repository.findByDataVenda(Integer.parseInt(ano),
				Integer.parseInt(Util.ajustarNumeroMes(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
		return dtoList;
	}

	@Override
	public BigDecimal calcularLucroBruto(List<Integer> idVenda) {
		return this.repository.calcularLucroBruto(idVenda);
	}

	@Override
	public Response cadastrar(Venda type) {
		return null;
	}

	@Override
	public Response excluir(Integer id) {
		Response response = new Response();
		try{
			this.compraVendaService.excluirCompraVendaPeloIdVenda(id);
			this.repository.delete(repository.getOne(id));
			response.setMensagem("Registro excluido com sucesso");
			response.setTipo(TipoResponse.SUCESSO);
		}catch (Exception e){
			response.setMensagem("Erro ao excluir registro");
			response.setTipo(TipoResponse.ERRO);
		}
		return response;
	}
}
