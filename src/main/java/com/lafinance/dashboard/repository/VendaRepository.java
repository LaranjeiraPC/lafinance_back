package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

	@Query("SELECT new Venda( "
			+ " v.investimento.acao.nomeAcao, "
			+ " v.quantidade, "
			+ " v.valorVenda, "
			+ " v.investimento.id) "
			+ " FROM Venda v "
			+ " where v.investimento.usuario.nomeUsuario = :usuario")
	List<Venda> consultarVendasPorUsuario(String usuario);

	@Query("SELECT new Venda( "
			+ " v.id, "
			+ " v.investimento.acao.nomeAcao, "
			+ " v.quantidade, "
			+ " v.totalVenda, "
			+ " v.valorVenda, "
			+ " v.lucroBruto, "
			+ " v.dataVenda, "			
			+ " v.compra, "
			+ " v.investimento.usuario.nomeUsuario "
			+ " ) FROM Venda v where v.investimento.id = :id")
	List<Venda> consultarVendasInvestimento(Integer id);

	@Query("Delete FROM Venda v " + " where v.id = :id")
	void excluirRegistro(Integer id);

}
