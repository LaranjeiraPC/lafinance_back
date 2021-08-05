package com.lafinance.dashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.AtivoDTO;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/ativo")
public class AtivoResource {

	private final Logger log = LoggerFactory.getLogger(AtivoResource.class);

	private final AtivoService ativoService;

	public AtivoResource(AtivoService ativoService) {
		this.ativoService = ativoService;
	}
	
	@CrossOrigin
	@PostMapping("/salvar/")
	public ResponseEntity<Response> salvarAtivo(@RequestBody Object[] ativo) {

		log.debug("API - Armazenar Ativo");

		try {
			return ResponseEntity.ok().body(ativoService.salvarAtivo(ativo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@PostMapping("/editar/")
	public ResponseEntity<Response> editarAtivo(@RequestBody Object[] ativo) {

		log.debug("API - editar Ativo");

		try {
			return ResponseEntity.ok().body(ativoService.editarAtivo(ativo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/excluir/{id}")
	public ResponseEntity<Response> excluirAtivo(@PathVariable(name = "id") String id) {

		log.debug("API - excluir Ativo");

		try {
			return ResponseEntity.ok().body(ativoService.excluirAtivo(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/")
	public ResponseEntity<List<AtivoDTO>> consultarAtivos() {
		log.debug("API - Consultar ativos");
		try {
			return ResponseEntity.ok().body(ativoService.consultarAtivo());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
