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

	@GetMapping("/exibir/{usuario}")
	public ResponseEntity<List<VendaDTO>> carregarVendas(
			@PathVariable(name = "usuario") String usuario) {
		log.debug("Carregar dados venda: {}", usuario);
		try {
			return ResponseEntity.ok().body(vendaService.consultarVendas(usuario));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/salvar/")
	public ResponseEntity<Response> salvarVenda(
			@RequestBody Object[] venda) {
		
		log.debug("Request to save Venda: {}", venda);
				
		try {
			return ResponseEntity.ok().body(vendaService.salvarVenda(venda));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/excluir/")
	public ResponseEntity<Response> excluirVenda(
			@RequestBody Object[] venda) {
		
		log.debug("Request to delete Venda: {}", venda);
				
		try {
			return ResponseEntity.ok().body(vendaService.excluirVenda(venda));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/exibir/det/{id}")
	public ResponseEntity<List<VendaDTO>> getLogin(
			@PathVariable(name = "id") Integer id) {
		log.debug("Carregar dados venda: {}", id);
		try {
			return ResponseEntity.ok().body(vendaService.consultarVenda(id));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
