package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.lafinance.dashboard.dto.AcaoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.DashDTO;
import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.CompraVenda;

@Service
@Transactional
public class DashServiceImpl implements DashService {

	private final Logger log = LoggerFactory.getLogger(DashServiceImpl.class);

	private final AcaoService acaoService;
	private final CompraVendaService compraVendaService;
	private final VendaService vendaService;
	private final ConfiguracaoService configuracaoService;

	private BigDecimal investimentoTotal = new BigDecimal("0.0");
	private BigDecimal brutoPago = new BigDecimal("0.0");
	private BigDecimal brutoRecebido = new BigDecimal("0.0");
	private Integer quantidadeTotal = 0;

	public DashServiceImpl(AcaoService acaoService, CompraVendaService compraVendaService, VendaService vendaService,
			ConfiguracaoService configuracaoService) {
		this.acaoService = acaoService;
		this.compraVendaService = compraVendaService;
		this.vendaService = vendaService;
		this.configuracaoService = configuracaoService;
	}

	@Override
	public DashDTO consultarDadosDahsboard() {
		log.debug("Calculando Investimento total");
		DashDTO dto = new DashDTO();
		LocalDate data = LocalDate.now();

		investimentoTotal = new BigDecimal("0.0");
		quantidadeTotal = 0;
		brutoRecebido = new BigDecimal("0.0");

		consultarInvestimentoTotal();
		consultarBrutoTotalMes(data);

		dto.setValorBrutoTotal(brutoRecebido.subtract(brutoPago));
		dto.setValorInvestimentoTotal(investimentoTotal);
		dto.setQuantidadeTotal(quantidadeTotal);
		dto.setValorBrutoMeta(configuracaoService.consultarDadosConfiguracao().getValorBrutoMeta());
		dto.setAtivoUm(configuracaoService.consultarDadosConfiguracao().getAtivoUm());
		dto.setAtivoDois(configuracaoService.consultarDadosConfiguracao().getAtivoDois());

		return dto;
	}

	private void consultarBrutoTotalMes(LocalDate data) {
		brutoPago = new BigDecimal("0.00");
		brutoRecebido = new BigDecimal("0.00");
		List<VendaDTO> vendas = vendaService.consultarVendasPeloAnoMesSelecionadoInteiro(String.valueOf(data.getYear()),
				String.valueOf(data.getMonthValue()));
		vendas.forEach(v -> {
			List<CompraVenda> compraVenda = compraVendaService.consultarCompraVendaPeloIdVenda(v.getId());
			compraVenda.forEach(c -> {
				brutoPago = brutoPago.add(c.getCompra().getValorBrutoPago());
				brutoRecebido = brutoRecebido.add(c.getVenda().getValorBrutoVenda());
			});
		});
	}

	private void consultarInvestimentoTotal() {
		List<AcaoDTO> acoes = acaoService.consultarAcoesAtivos();
		if (!acoes.isEmpty()) {
			acoes.forEach(a -> {
				investimentoTotal = investimentoTotal.add(a.getValorBrutoPago());
				quantidadeTotal = quantidadeTotal + a.getQuantidade();
			});
		}
	}

}
