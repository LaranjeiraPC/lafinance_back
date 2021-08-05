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

}
