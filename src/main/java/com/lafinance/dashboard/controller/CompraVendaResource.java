package com.lafinance.dashboard.controller;

import java.net.URI;
import java.util.List;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.service.CompraVendaService;

@CrossOrigin
@RestController
@RequestMapping("/api/compravenda")
public class CompraVendaResource {
	
	private final Logger log = LoggerFactory.getLogger(CompraVendaResource.class);

	private final CompraVendaService compraVendaService;

	public CompraVendaResource(CompraVendaService compraVendaService) {
		this.compraVendaService = compraVendaService;
	}
	
	@CrossOrigin
	@GetMapping("/consulta/{id}")
	public ResponseEntity<List<CompraVenda>> consultarDetalhesVenda(@PathVariable(name = "id") String id) {
		log.debug("API - Consultar compras vendas pelo id");
		try {
			return ResponseEntity.ok().body(compraVendaService.consultarCompraVendaPeloIdVenda(Integer.parseInt(id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	@CrossOrigin
	@PostMapping("/salvar/")
	public ResponseEntity<Response> salvar(@RequestBody List<CompraVenda> compraVenda) {
		try {
			compraVendaService.salvarRegistro(compraVenda);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
