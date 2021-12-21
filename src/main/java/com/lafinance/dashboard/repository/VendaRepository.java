package com.lafinance.dashboard.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

	@Query("SELECT a FROM Venda a where date_part('year', a.dataVenda) = :ano and date_part('month', a.dataVenda) = :mes")
	List<Venda> findByDataVenda(@Param("ano") Integer ano, @Param("mes") Integer mes);

	@Query("SELECT SUM(v.valorBrutoVenda) FROM Venda v where v.id = :idsVenda")
	BigDecimal calcularLucroBruto(List<Integer> idsVenda);
	
}
