package com.lafinance.dashboard.repository;

import com.lafinance.dashboard.domain.model.CompraVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseRepository extends JpaRepository<CompraVenda, Integer> {

    List<CompraVenda> findByVendaId(Integer idVenda);

}
