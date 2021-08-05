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

	@CrossOrigin
	@PostMapping("/compra/")
	public ResponseEntity<Response> salvarCompra(@RequestBody Object[] acao) {

		log.debug("API - Armazenar compra");

		try {
			return ResponseEntity.ok().body(acaoService.salvarCompra(acao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@CrossOrigin
	@GetMapping("/consulta/ano/mes/{ano}/{mes}")
	public ResponseEntity<List<AcaoDTO>> consultarAcoesPeloMesSelecionado(@PathVariable(name = "ano") String ano,
			@PathVariable(name = "mes") String mes) {
		log.debug("API - Consultar ações pelo ano e mês");
		try {
			return ResponseEntity.ok().body(acaoService.consultarAcoesPeloAnoMesSelecionado(ano, mes));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/acoes/{mes}")
	public ResponseEntity<List<AcaoDTO>> consultarAcoesAtivos(@PathVariable(name = "mes") String mes) {
		log.debug("API - Consultar ações dos meses anteriores ativos");
		try {
			return ResponseEntity.ok().body(acaoService.consultarAcoesAtivos(mes));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/ano/")
	public ResponseEntity<List<String>> consultarAno() {
		log.debug("API - Consultar ano");
		try {
			return ResponseEntity.ok().body(acaoService.consultarAno());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/mes/{ano}")
	public ResponseEntity<List<String>> consultarMes(@PathVariable(name = "ano") String ano) {
		log.debug("API - Consultar mes");
		try {
			return ResponseEntity.ok().body(acaoService.consultarMes(ano));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/id/{id}")
	public ResponseEntity<AcaoDTO> consultarAcaoVenda(
			@PathVariable(name = "id") String id) {
		log.debug("API - Consultar Entidade Acao venda");
		try {
			return ResponseEntity.ok().body(acaoService.consultarAcaoVenda(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/acoes/id/{id}")
	public ResponseEntity<List<AcaoDTO>> consultarAcoesVenda(
			@PathVariable(name = "id") String id) {
		log.debug("API - Consultar Endidades Ações venda");
		try {
			return ResponseEntity.ok().body(acaoService.consultarAcoesVenda(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/acoes/ativo/{ativo}")
	public ResponseEntity<List<AcaoDTO>> consultarAcoesAtivo(
			@PathVariable(name = "ativo") String ativo) {
		log.debug("API - Consultar Entidades Ações pelo ativo");
		try {
			return ResponseEntity.ok().body(acaoService.consultarAcoesAtivo(ativo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
