package com.lafinance.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.ConfiguracaoDTO;
import com.lafinance.dashboard.service.ConfiguracaoService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/configuracao")
public class ConfiguracaoResource {
	private final ConfiguracaoService configuracaoService;

	public ConfiguracaoResource(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}

	@CrossOrigin
	@PostMapping("/salvar/")
	public ResponseEntity<Response> salvarConfiguracao(@RequestBody Object[] configuracao) {
		try {
			return ResponseEntity.ok().body(configuracaoService.salvarConfiguracao(configuracao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@CrossOrigin
	@GetMapping("/consulta/")
	public ResponseEntity<ConfiguracaoDTO> consultarDadosConfiguracao() {
		try {
			return ResponseEntity.ok().body(configuracaoService.consultarDadosConfiguracao());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
