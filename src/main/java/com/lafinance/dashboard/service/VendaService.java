package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.util.List;

import com.lafinance.dashboard.domain.dto.VendaDTO;
public interface VendaService extends CommonService<VendaDTO> {
	List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes) throws Exception;
	List<VendaDTO> consultarVendasPeloAnoMesSelecionadoInteiro(String ano, String mes);
	BigDecimal calcularLucroBruto(List<Integer> idVenda) throws Exception;
}
