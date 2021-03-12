package com.lafinance.dashboard.service;

import com.lafinance.dashboard.dto.UsuarioDTO;

public interface UsuarioService {
	UsuarioDTO consultarUsuario(String usuario, String senha);
}
