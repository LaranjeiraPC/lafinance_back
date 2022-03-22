package com.lafinance.dashboard.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.domain.model.CompraVenda;

@Repository
public interface CompraVendaRepository extends JpaRepository<CompraVenda, Integer> {
    List<CompraVenda> findByVendaId(Integer id);

    @Query("SELECT v FROM CompraVenda v where date_part('year', v.venda.dataVenda) = :ano order by v.venda.dataVenda asc")
    List<CompraVenda> findByDataVenda(@Param("ano") Integer ano);

    @Query("SELECT sum(v.venda.valorBrutoVenda - v.compra.valorBrutoPago) FROM CompraVenda v " +
            " where date_part('year', v.venda.dataVenda) = :ano")
    BigDecimal calculoLucroAno(@Param("ano") Integer ano);

    @Query("SELECT sum(v.venda.valorBrutoVenda - v.compra.valorBrutoPago) FROM CompraVenda v " +
            " where v.venda.id = :id")
    BigDecimal calculoLucroBrutoPeloIdVenda(@Param("id") Integer idVenda);

}
