package com.lafinance.dashboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.model.Log;
import com.lafinance.dashboard.service.LogService;

@CrossOrigin
@RestController
@RequestMapping("/api/log")
public class LogResource {

	private final LogService logService;

	public LogResource(LogService logService) {
		this.logService = logService;
	}

	@GetMapping("/exibir/{usuario}")
	public ResponseEntity<List<Log>> getLogin(
			@PathVariable(name = "usuario") String usuario) {
		try {
			return ResponseEntity.ok().body(logService.consultarLogUsuario(usuario));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
