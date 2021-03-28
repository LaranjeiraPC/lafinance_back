package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {	
	
	@Query("SELECT c FROM Compra c where c.investimento = :investimento and c.indicadorAtivo = 'S'")
	List<Compra> consultarInvestimento(Investimento investimento);
	
	@Query("SELECT c FROM Compra c where c.investimento.id = :investimento and c.indicadorAtivo = 'S' ")
	List<Compra> consultarInvestimentoPorId(Integer investimento);

	@Query("SELECT c FROM Compra c where c.id = :id")
	Compra consultarCompra(Integer id);
	
}
