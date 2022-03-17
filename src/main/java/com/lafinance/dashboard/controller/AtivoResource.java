package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.model.Ativo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.dto.AtivoDTO;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/ativo")
public class AtivoResource {

	@Autowired
	private AtivoService ativoService;

	@CrossOrigin
	@PostMapping("/salva/")
	public ResponseEntity<Response> cadastrarAtivo(@RequestBody Ativo ativo) {
		try {
			return ResponseEntity.ok().body(ativoService.salvarAtivo(ativo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@PostMapping("/editar/")
	public ResponseEntity<Response> editarAtivo(@RequestBody Ativo ativo) {
		try {
			return ResponseEntity.ok().body(ativoService.editarAtivo(ativo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/excluir/{id}")
	public ResponseEntity<Response> excluirAtivo(@PathVariable(name = "id") Integer id) {
		try {
			return ResponseEntity.ok().body(ativoService.excluirAtivo(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/consulta/")
	public ResponseEntity<List<AtivoDTO>> consultarAtivos() {
		try {
			return ResponseEntity.ok().body(ativoService.consultarAtivo());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
