package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Usuario;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {
	
	@Query("SELECT a FROM Acao a where a.usuario = :usuario")
	List<Acao> consultarAcoes(Usuario usuario);
	
	@Query("SELECT a FROM Acao a where a.usuario = :usuario and a.nomeAcao = :acao")
	Acao consultarAcao(Usuario usuario, String acao);
	
	@Query("SELECT a FROM Acao a where a.id = :id")
	Acao consultarAcaoPeloId(Integer id);

}
