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

	@Query("SELECT new Investimento( "
			+ " i.id, "
			+ " i.acao.nomeAcao, "
			+ " i.quantidade, "
			+ " i.ultimoValorCompra) FROM Investimento i where i.usuario.nomeUsuario = :usuario and i.quantidade > 0 ")
	List<Investimento> consultarListaInvestimento(String usuario);
	
	@Query("SELECT i FROM Investimento i where i.usuario = :usuario and i.acao = :acao")
	Investimento consultarInvestimento(Usuario usuario, Acao acao);
	
	@Query("SELECT i FROM Investimento i where i.id = :id")
	Investimento consultarInvestimentoPorID(Integer id);
}
