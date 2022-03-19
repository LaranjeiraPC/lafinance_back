package com.lafinance.dashboard.service.impl;

import com.lafinance.dashboard.domain.dto.RelatorioCompraDTO;
import com.lafinance.dashboard.service.RelatorioCompraService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RelatorioCompraServiceImpl implements RelatorioCompraService {

    @Override
    public List<RelatorioCompraDTO> consultarDadosCompraPorMes(Integer mes, Integer ano) {
        return null;
    }

    @Override
    public List<RelatorioCompraDTO> consultarDadosCompraPorAno(Integer ano) {
        return null;
    }
}
