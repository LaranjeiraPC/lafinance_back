package com.lafinance.dashboard.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.UsuarioDTO;
import com.lafinance.dashboard.repository.UsuarioRepository;
import com.lafinance.dashboard.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	/**
	 *Consultar usuario válido
	 */
	@Override
	public UsuarioDTO consultarUsuario(String usuario, String senha) {
		return new UsuarioDTO(repository.consultarUsuario(usuario, senha));
	}

}

