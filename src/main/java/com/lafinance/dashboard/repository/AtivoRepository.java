package com.lafinance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Ativo;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Integer> {

	Ativo findByNome(String nome);
	
}
