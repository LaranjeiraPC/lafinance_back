package com.lafinance.dashboard.service;

import com.lafinance.dashboard.domain.dto.RelatorioCompraDTO;

import java.util.List;

public interface RelatorioCompraService {
    List<RelatorioCompraDTO> consultarDadosCompraPorMes(Integer mes, Integer ano);
    List<RelatorioCompraDTO> consultarDadosCompraPorAno(Integer ano);
}
