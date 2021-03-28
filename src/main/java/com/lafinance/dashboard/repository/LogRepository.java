package com.lafinance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {	

	@Query("SELECT l FROM Log l where l.usuario.nomeUsuario = :usuario")
	List<Log> consultarListaLog(String usuario);
	
}
