package com.lafinance.dashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.CompraDTO;
import com.lafinance.dashboard.service.CompraService;

@CrossOrigin
@RestController
@RequestMapping("/api/compra")
public class CompraResource {

	private final Logger log = LoggerFactory.getLogger(CompraResource.class);

	private final CompraService compraService;

	public CompraResource(CompraService compraService) {
		this.compraService = compraService;
	}
	
	@GetMapping("/exibir/{investimento}")
	public ResponseEntity<List<CompraDTO>> getLogin(
			@PathVariable(name = "investimento") Integer investimento) {
		log.debug("Carregar dados compra: {}", investimento);
		try {
			return ResponseEntity.ok().body(compraService.consultarCompras(investimento));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}

}
