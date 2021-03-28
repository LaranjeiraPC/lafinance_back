package com.lafinance.dashboard.service;

import com.lafinance.dashboard.dto.UsuarioDTO;
import com.lafinance.dashboard.model.Usuario;

public interface UsuarioService {
	
	UsuarioDTO consultarUsuario(String usuario, String senha);
	
	Usuario consultarNome(String usuario);
	
}
