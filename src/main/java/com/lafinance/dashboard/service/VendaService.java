package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.util.List;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.model.Venda;
import com.lafinance.dashboard.util.Response;

public interface VendaService extends CommonService<Venda> {
	List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes);
	List<VendaDTO> consultarVendasPeloAnoMesSelecionadoInteiro(String ano, String mes);
	BigDecimal calcularLucroBruto(List<Integer> idVenda);
}
