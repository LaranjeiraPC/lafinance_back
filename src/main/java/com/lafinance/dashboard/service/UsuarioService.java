package com.lafinance.dashboard.service;

import com.lafinance.dashboard.model.Usuario;

public interface UsuarioService {
	Usuario consultarUsuario(String usuario, String senha);
}
