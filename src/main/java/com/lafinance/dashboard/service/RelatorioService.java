package com.lafinance.dashboard.service;

import com.lafinance.dashboard.domain.dto.RelatorioDTO;

import java.util.List;
import java.util.Map;

public interface RelatorioService {
    List<RelatorioDTO> consultarDadosPorMes(Integer mes, Integer ano);
    Map<String, List<RelatorioDTO>> consultarDadosPorAno(Integer ano);
}
