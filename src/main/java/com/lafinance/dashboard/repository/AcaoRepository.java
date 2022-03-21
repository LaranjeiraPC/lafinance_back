package com.lafinance.dashboard.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.domain.model.Acao;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {

    @Query("SELECT a FROM Acao a where date_part('year', a.dataCompra) = :ano and date_part('month', a.dataCompra) = :mes and a.status = 'S'")
    List<Acao> consultarAcoesAtivosMesCorrente(@Param("ano") Integer ano, @Param("mes") Integer mes);

    //	@Query("select a from acao a where a.id not in (:ids) and a.status = 's'")
    List<Acao> findByIdNotInAndStatus(List<Integer> ids, String status);

    @Query("SELECT a FROM Acao a where a.status = 'S'")
    List<Acao> findByAllAndStatus();

    @Query("SELECT c.compra FROM CompraVenda c where c.venda.id = :id")
    List<Acao> findByVenda(Integer id);

    List<Acao> findByAtivoNome(String nome);

    Optional<Acao> findById(Integer id);

    List<Acao> findByIdIn(List<Integer> ids);

    @Query("SELECT sum(a.valorBrutoPago) FROM Acao a where a.status = 'S' ")
    BigDecimal consultarValorInvestimentoTotal();

    @Query("SELECT sum(a.quantidade) FROM Acao a where a.status = 'S' ")
    Integer consultarQuantidadeCotas();
}
