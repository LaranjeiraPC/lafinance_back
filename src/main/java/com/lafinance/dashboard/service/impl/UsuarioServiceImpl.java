package com.lafinance.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.UsuarioDTO;
import com.lafinance.dashboard.domain.model.Usuario;
import com.lafinance.dashboard.repository.UsuarioRepository;
import com.lafinance.dashboard.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UsuarioDTO consultarUsuario(String usuario, String senha) {
		return new UsuarioDTO(repository.consultarUsuario(usuario, senha));
	}

	@Override
	public Usuario consultarNome(String usuario) {
		return repository.consultarNome(usuario);
	}

}

