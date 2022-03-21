package com.lafinance.dashboard.service;

import java.math.BigDecimal;
import java.util.List;

import com.lafinance.dashboard.domain.model.CompraVenda;

public interface CompraVendaService {
    void salvarRegistro(List<CompraVenda> compraVenda) throws Exception;

    List<CompraVenda> consultarCompraVendaPeloIdVenda(Integer id);

    void excluirCompraVendaPeloIdVenda(Integer id) throws Exception;

    BigDecimal calcularLucroBrutoTotalPeloIdVenda(Integer idVenda) throws Exception;

    BigDecimal calcularLucroBrutoAno(Integer ano);

}
