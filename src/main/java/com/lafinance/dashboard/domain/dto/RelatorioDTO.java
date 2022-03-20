package com.lafinance.dashboard.domain.dto;

import com.lafinance.dashboard.domain.model.CompraVenda;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RelatorioDTO implements Serializable {

    private Integer ano;
    private String mes;
    private String ativo;
    private Integer quantidadeComprado;
    private BigDecimal valorAtivoPago;
    private BigDecimal valorBrutoPago;
    private LocalDate dataCompra;
    private Integer quantidadeVendido;
    private BigDecimal valorAtivoVenda;
    private BigDecimal valorBrutoVenda;
    private LocalDate dataVenda;
    private BigDecimal lucroBrutoTotal;

    public RelatorioDTO() {
    }

    public RelatorioDTO(CompraVenda compraVenda) {
        setAno(compraVenda.getVenda().getDataVenda().getYear());
        setMes(compraVenda.getVenda().getDataVenda().getMonth().name());
        setAtivo(compraVenda.getVenda().getAtivo().getNome());
        setValorAtivoPago(compraVenda.getCompra().getValorAtivoPago());
        setValorBrutoPago(compraVenda.getCompra().getValorBrutoPago());
        setQuantidadeComprado(compraVenda.getCompra().getQuantidade());
        setDataCompra(compraVenda.getCompra().getDataCompra());
        setValorAtivoVenda(compraVenda.getVenda().getValorAtivoVenda());
        setValorBrutoVenda(compraVenda.getVenda().getValorBrutoVenda());
        setQuantidadeVendido(compraVenda.getVenda().getQuantidade());
        setDataVenda(compraVenda.getVenda().getDataVenda());
    }

}
