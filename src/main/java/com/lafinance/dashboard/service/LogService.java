package com.lafinance.dashboard.service;

import java.util.List;

import com.lafinance.dashboard.model.Log;
import com.lafinance.dashboard.model.Usuario;

public interface LogService {

	List<Log> consultarLogUsuario(String usuario);
	
	void salvarLog(Usuario usuario, String log);
	
}
