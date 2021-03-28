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

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/acao")
public class AcaoResource {
	
	private final Logger log = LoggerFactory.getLogger(AcaoResource.class);

	private final AcaoService acaoService;

	public AcaoResource(AcaoService acaoService) {
		this.acaoService = acaoService;
	}

	@GetMapping("/exibir/lista/acao/{usuario}")
	public ResponseEntity<List<Acao>> consultarListaAcao(
			@PathVariable(name = "usuario") String usuario) {
		try {
			return ResponseEntity.ok().body(acaoService.carregarAcoes(usuario));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/exibir/lista/acao/nome/{usuario}")
	public ResponseEntity<List<AcaoDTO>> consultarListaAcaoNome(
			@PathVariable(name = "usuario") String usuario) {
		try {
			return ResponseEntity.ok().body(acaoService.carregarAcoesNome(usuario));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/salvar/")
	public ResponseEntity<Response> save(
			@RequestBody Object[] acao) {
		
		log.debug("Request to save Ativo: {}", acao);
				
		try {
			return ResponseEntity.ok().body(acaoService.salvarAcao(acao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PostMapping("/editar/")
	public ResponseEntity<Response> edit(@RequestBody Object[] acao) {

		log.debug("Request to edit Ativo: {}", acao);

		try {
			return ResponseEntity.ok().body(acaoService.editarAcao(acao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("/delete/")
	public ResponseEntity<Response> delete(@RequestBody Object[] acao) {

		log.debug("Request to delete Ativo: {}", acao);

		try {
			return ResponseEntity.ok().body(acaoService.excluirAcao(acao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
}
