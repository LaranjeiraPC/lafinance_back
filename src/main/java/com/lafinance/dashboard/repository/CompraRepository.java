package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {	
	
	@Query("SELECT c FROM Compra c where c.investimento = :investimento")
	List<Compra> consultarInvestimento(Investimento investimento);

}
