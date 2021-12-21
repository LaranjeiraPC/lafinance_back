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

	List<Acao> findByStatus(String status);

	@Query("SELECT a FROM Acao a where date_part('year', a.dataCompra) = :ano and a.status = 'S'")
	List<Acao> consultarMeses(@Param("ano") Integer ano);

	@Query("SELECT a FROM Acao a where date_part('year', a.dataCompra) = :ano and date_part('month', a.dataCompra) = :mes and a.status = 'S'")
	List<Acao> findByDataCompraAndStatus(@Param("ano") Integer ano, @Param("mes") Integer mes);
	
	@Query("SELECT a FROM Acao a where a.status = 'S'")
	List<Acao> findByAllAndStatus();
	
	List<Acao> findByAtivoNomeAndStatus(String nome, String status);

	@Query("SELECT a FROM Acao a where a.id = :id")
	List<Acao> consultarAcoesId(@Param("id") List<Integer> id);

	@Query("SELECT SUM(a.valorBrutoPago) FROM Acao a where a.id = :idsCompra")
	BigDecimal calcularValorBrutoPago(List<Integer> idsCompra);

}
