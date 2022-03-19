package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.lafinance.dashboard.domain.dto.AcaoDTO;
import com.lafinance.dashboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.DashDTO;
import com.lafinance.dashboard.domain.dto.VendaDTO;
import com.lafinance.dashboard.domain.model.CompraVenda;

@Service
@Transactional
public class DashServiceImpl implements DashService {

	@Autowired
	private AcaoService acaoService;
	@Autowired

	private CompraVendaService compraVendaService;

	@Autowired
	private VendaService vendaService;

	@Autowired
	private ConfiguracaoService configuracaoService;

	private BigDecimal investimentoTotal = new BigDecimal("0.0");
	private BigDecimal brutoPago = new BigDecimal("0.0");
	private BigDecimal brutoRecebido = new BigDecimal("0.0");
	private Integer quantidadeTotal = 0;

	@Override
	public DashDTO consultarDadosDahsboard() throws Exception {
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

	private void consultarInvestimentoTotal() throws Exception {
		List<AcaoDTO> acoes = acaoService.listarAcoesAtivos();
		if (!acoes.isEmpty()) {
			acoes.forEach(a -> {
				investimentoTotal = investimentoTotal.add(a.getValorBrutoPago());
				quantidadeTotal = quantidadeTotal + a.getQuantidade();
			});
		}
	}

}
