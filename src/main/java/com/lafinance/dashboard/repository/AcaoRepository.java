package com.lafinance.dashboard.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Acao;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {

	@Query("SELECT a FROM Acao a where date_part('year', a.dataCompra) = :ano and date_part('month', a.dataCompra) = :mes and a.status = 'S'")
	List<Acao> consultarAcoesAtivosMesCorrente(@Param("ano") Integer ano, @Param("mes") Integer mes);

	@Query("SELECT a FROM Acao a where date_part('year', a.dataCompra) != :ano and date_part('month', a.dataCompra) != :mes and a.status = 'S'")
	List<Acao> consultarAcoesAtivosOutrosMeses(@Param("ano") Integer ano, @Param("mes") Integer mes);
	
	@Query("SELECT a FROM Acao a where a.status = 'S'")
	List<Acao> findByAllAndStatus();

	@Query("SELECT a FROM Acao a where a.id = :id")
	List<Acao> consultarAcoesId(@Param("id") List<Integer> id);

	@Query("SELECT SUM(a.valorBrutoPago) FROM Acao a where a.id = :idsCompra")
	BigDecimal calcularValorBrutoPago(List<Integer> idsCompra);

	@Query("SELECT c.compra FROM CompraVenda c where c.venda.id = :id")
	List<Acao> findByVenda(Integer id);

	List<Acao> findByAtivoNome(String nome);

}
