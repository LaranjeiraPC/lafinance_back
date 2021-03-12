package com.lafinance.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.UsuarioDTO;
import com.lafinance.dashboard.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class UsuarioResource {

	private final UsuarioService usuarioService;

	public UsuarioResource(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/autenticar/{usuario}/{senha}")
	public ResponseEntity<UsuarioDTO> getLogin(
			@PathVariable(name = "usuario") String usuario,
			@PathVariable(name = "senha") String senha) {
		try {
			return ResponseEntity.ok().body(usuarioService.consultarUsuario(usuario, senha));
		} catch (Exception E) {
			return ResponseEntity.badRequest().build();
		}
	}
}
