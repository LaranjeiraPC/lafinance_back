package com.lafinance.dashboard.repository;

import java.util.List;

import com.lafinance.dashboard.model.Acao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.CompraVenda;

@Repository
public interface CompraVendaRepository extends JpaRepository<CompraVenda, Integer> {
	List<CompraVenda> findByVendaId(Integer id);
	List<CompraVenda> findByCompraId(Integer id);

//	@EntityGraph(attributePaths = "employeeGroups")
	@Query("SELECT c.compra FROM CompraVenda c where c.venda.id = :id")
	List<Acao> findByVenda(Integer id);
}
