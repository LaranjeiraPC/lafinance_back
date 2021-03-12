package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.model.Usuario;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Integer> {	

	@Query("SELECT i FROM Investimento i where i.usuario = :usuario")
	List<Investimento> consultarListaInvestimento(Usuario usuario);
	
	@Query("SELECT i FROM Investimento i where i.usuario = :usuario and i.acao = :acao")
	Investimento consultarInvestimento(Usuario usuario, Acao acao);
}
