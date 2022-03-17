package com.lafinance.dashboard.service.impl;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.model.Venda;
import com.lafinance.dashboard.repository.AnaliseRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AnaliseService;
import com.lafinance.dashboard.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnaliseServiceImpl implements AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private AcaoService acaoService;

    @Override
    public BigDecimal calcularLucroBruto(Integer idVenda) {
        List<CompraVenda> compraVendaList = this.analiseRepository.findByVendaId(idVenda);
        List<Integer> idsVenda = compraVendaList.stream().map(CompraVenda::getVenda).map(Venda::getId).collect(Collectors.toList());
        List<Integer> idsCompra = compraVendaList.stream().map(CompraVenda::getCompra).map(Acao::getId).collect(Collectors.toList());

        BigDecimal valorVendaBruto = this.vendaService.calcularLucroBruto(idsVenda);
        BigDecimal valorCompraBruto = this.acaoService.calcularLucroBruto(idsCompra);

        return valorVendaBruto.subtract(valorCompraBruto);
    }

}
