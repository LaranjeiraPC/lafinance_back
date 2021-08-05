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

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.service.VendaService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/venda")
public class VendaResource {

	private final Logger log = LoggerFactory.getLogger(VendaResource.class);

	private final VendaService vendaService;

	public VendaResource(VendaService vendaService) {
		this.vendaService = vendaService;
	}

	@CrossOrigin
	@PostMapping("/venda/")
	public ResponseEntity<Response> salvarVenda(@RequestBody Object[] acao) {

		log.debug("API - Armazenar venda");

		try {
			return ResponseEntity.ok().body(vendaService.salvarVenda(acao));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@CrossOrigin
	@GetMapping("/consulta/ano/mes/{ano}/{mes}")
	public ResponseEntity<List<VendaDTO>> consultarVendasPeloMesSelecionado(@PathVariable(name = "ano") String ano,
			@PathVariable(name = "mes") String mes) {
		log.debug("API - Consultar vendas pelo ano e mês");
		try {
			return ResponseEntity.ok().body(vendaService.consultarVendasPeloAnoMesSelecionado(ano, mes));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/detalhes/{id}")
	public ResponseEntity<VendaDTO> consultarDetalhesVenda(@PathVariable(name = "id") String id) {
		log.debug("API - Consultar vendas pelo id");
		try {
			return ResponseEntity.ok().body(vendaService.consultarDetalhesVenda(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
