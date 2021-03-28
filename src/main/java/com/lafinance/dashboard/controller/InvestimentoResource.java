package com.lafinance.dashboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.InvestimentoDTO;
import com.lafinance.dashboard.service.InvestimentoService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/investimento")
public class InvestimentoResource {

	private final InvestimentoService investimentoService;

	public InvestimentoResource(InvestimentoService investimentoService) {
		this.investimentoService = investimentoService;
	}

	@PostMapping("/salvar/")
	public ResponseEntity<Response> save(@RequestBody Object[] compra) {
		try {
			return ResponseEntity.ok().body(investimentoService.salvarInvestimento(compra));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/exibir/{usuario}")
	public ResponseEntity<List<InvestimentoDTO>> getLogin(
			@PathVariable(name = "usuario") String usuario) {
		try {
			return ResponseEntity.ok().body(investimentoService.consultarInvestimento(usuario));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
}
