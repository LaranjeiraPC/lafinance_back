package com.lafinance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lafinance.dashboard.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT new Usuario(u) FROM Usuario u where u.nomeUsuario = :usuario and u.senhaUsuario = :senha and u.ativoUsuario = 'A'")
	Usuario consultarUsuario(String usuario, String senha);

	@Query("SELECT new Usuario(u) FROM Usuario u where u.nomeUsuario = :usuario")
	Usuario consultarNome(String usuario);

}
