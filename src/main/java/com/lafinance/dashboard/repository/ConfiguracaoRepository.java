package com.lafinance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Integer> {
	
}
