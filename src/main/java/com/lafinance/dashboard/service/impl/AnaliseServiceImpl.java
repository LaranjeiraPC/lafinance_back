package com.lafinance.dashboard.service.impl;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.model.Venda;
import com.lafinance.dashboard.repository.AnaliseRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AnaliseService;
import com.lafinance.dashboard.service.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnaliseServiceImpl implements AnaliseService {

    private final Logger log = LoggerFactory.getLogger(AnaliseServiceImpl.class);

    private final AnaliseRepository analiseRepository;
    private final VendaService vendaService;
    private final AcaoService acaoService;

    public AnaliseServiceImpl(AnaliseRepository analiseRepository, VendaService vendaService, AcaoService acaoService){
        this.analiseRepository = analiseRepository;
        this.vendaService = vendaService;
        this.acaoService = acaoService;
    }

    @Override
    public BigDecimal calcularLucroBruto(Integer idVenda) {
        log.debug("Calculando lucro bruto");
        log.trace("Id da venda {}", idVenda);

        List<CompraVenda> compraVendaList = this.analiseRepository.findByVendaId(idVenda);
        log.trace("Total de compra venda encontrado {}", compraVendaList);

        List<Integer> idsVenda = compraVendaList.stream().map(CompraVenda::getVenda).map(Venda::getId).collect(Collectors.toList());
        List<Integer> idsCompra = compraVendaList.stream().map(CompraVenda::getCompra).map(Acao::getId).collect(Collectors.toList());

        BigDecimal valorVendaBruto = this.vendaService.calcularLucroBruto(idsVenda);
        BigDecimal valorCompraBruto = this.acaoService.calcularLucroBruto(idsCompra);

        return valorVendaBruto.subtract(valorCompraBruto);
    }

}
