package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.domain.model.CompraVenda;
import com.lafinance.dashboard.service.CompraVendaService;

@CrossOrigin
@RestController
@RequestMapping("/api/compravenda")
public class CompraVendaResource {

	@Autowired
	private CompraVendaService compraVendaService;

	@CrossOrigin
	@GetMapping("/consulta/{id}")
	public ResponseEntity<List<CompraVenda>> consultarDetalhesVenda(@PathVariable(name = "id") String id) {
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
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
