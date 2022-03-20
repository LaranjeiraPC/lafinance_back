package com.lafinance.dashboard.service.impl;

import com.lafinance.dashboard.domain.dto.RelatorioDTO;
import com.lafinance.dashboard.repository.CompraVendaRepository;
import com.lafinance.dashboard.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class RelatorioServiceImpl implements RelatorioService {

    private final CompraVendaRepository compraVendaRepository;

    @Override
    public List<RelatorioDTO> consultarDadosPorMes(Integer mes, Integer ano) {

        return null;
    }

    @Override
    public Map<String, List<RelatorioDTO>> consultarDadosPorAno(Integer ano) {
        var compraVendaConsultado = this.compraVendaRepository.findByDataVenda(ano);

        Map<String, List<RelatorioDTO>> relatorio = compraVendaConsultado.stream().map(cv -> {
            var relatorioVenda = new RelatorioDTO(cv);
            relatorioVenda.setLucroBrutoTotal(relatorioVenda.getValorBrutoVenda().subtract(relatorioVenda.getValorBrutoPago()));
            return relatorioVenda;
        }).collect(Collectors.groupingBy(RelatorioDTO::getMes));

        return relatorio;
    }
}
