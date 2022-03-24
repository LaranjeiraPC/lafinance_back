package com.lafinance.dashboard.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Query("SELECT c.venda.ativo.nome FROM CompraVenda c group by c.venda.ativo.nome ")
    List<String> listarAtivosAgrupandoByNomeAtivo();

    @Query("SELECT c.venda.dataVenda FROM CompraVenda c group by c.venda.dataVenda order by c.venda.dataVenda ")
    List<LocalDate> listarAnos();

    @Query("SELECT c.venda.dataVenda FROM CompraVenda c where date_part('year', c.venda.dataVenda) = :ano group by c.venda.dataVenda order by c.venda.dataVenda ")
    List<LocalDate> listarMeses(@Param("ano") Integer ano);

}
