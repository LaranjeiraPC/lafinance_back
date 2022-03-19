package com.lafinance.dashboard.service;

import com.lafinance.dashboard.domain.dto.UsuarioDTO;
import com.lafinance.dashboard.domain.model.Usuario;

public interface UsuarioService {
	
	UsuarioDTO consultarUsuario(String usuario, String senha);
	
	Usuario consultarNome(String usuario);
	
}
