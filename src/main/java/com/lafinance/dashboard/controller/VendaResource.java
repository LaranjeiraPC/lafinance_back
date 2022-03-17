package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.service.VendaService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/venda")
public class VendaResource {

	@Autowired
	private VendaService vendaService;

	@CrossOrigin
	@PostMapping("/venda/")
	public ResponseEntity<Response> salvarVenda(@RequestBody Venda venda) {
		try {
			return ResponseEntity.ok().body(vendaService.cadastrar(venda));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@CrossOrigin
	@GetMapping("/consulta/ano/mes/{ano}/{mes}")
	public ResponseEntity<List<VendaDTO>> consultarVendasPeloMesSelecionado(@PathVariable(name = "ano") String ano,
			@PathVariable(name = "mes") String mes) {
		try {
			return ResponseEntity.ok().body(vendaService.consultarVendasPeloAnoMesSelecionado(ano, mes));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@CrossOrigin
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Response> excluirVenda(@PathVariable(name = "id") Integer id) {
		try {
			return ResponseEntity.ok().body(vendaService.excluir(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@CrossOrigin
	@PostMapping("/edita/")
	public ResponseEntity<Response> editarVenda(
			@RequestBody Venda venda) {
		try {
			return ResponseEntity.ok().body(vendaService.editar(venda));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
