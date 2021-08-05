package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.CompraVenda;

@Repository
public interface CompraVendaRepository extends JpaRepository<CompraVenda, Integer> {
	List<CompraVenda> findByVendaId(Integer id);
	List<CompraVenda> findByCompraId(Integer id);
}
