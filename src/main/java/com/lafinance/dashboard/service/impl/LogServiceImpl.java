package com.lafinance.dashboard.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.model.Log;
import com.lafinance.dashboard.model.Usuario;
import com.lafinance.dashboard.repository.LogRepository;
import com.lafinance.dashboard.service.LogService;

@Service
@Transactional
public class LogServiceImpl implements LogService{

	private final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);
	
	private final LogRepository repository;
	
	public LogServiceImpl(LogRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Log> consultarLogUsuario(String usuario) {
		log.debug("Consultar log(s): {}", usuario);
		return repository.consultarListaLog(usuario);
	}

	@Override
	public void salvarLog(Usuario usuario, String msg) {
		log.debug("Armazenando log do usuario: {}", usuario.getNomeUsuario());
		
		Log logClass = new Log();
		logClass.setDescricao(msg);
		logClass.setUsuario(usuario);
		logClass.setDataCriacao(LocalDate.now());

		repository.save(logClass);
		
	}
	
}
