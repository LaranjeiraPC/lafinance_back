package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Acao;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Integer> {
	
	@Query("SELECT a FROM Acao a where a.usuario.nomeUsuario = :usuario")
	List<Acao> consultarAcoes(String usuario);
	
	@Query("SELECT new Acao(a.nomeAcao) FROM Acao a where a.usuario.nomeUsuario = :usuario and a.ativoAcao = 'A'")
	List<Acao> consultarAcoesNome(String usuario);
	
	@Query("SELECT a FROM Acao a where a.usuario.nomeUsuario = :usuario and a.nomeAcao = :acao")
	Acao consultarAcao(String usuario, String acao);
	
	@Query("SELECT a FROM Acao a where a.id = :id")
	Acao consultarAcaoPeloId(Integer id);
	
	@Query("SELECT a FROM Acao a where a.nomeAcao = :nome")
	Acao consultarAcaoNome(String nome);

}
