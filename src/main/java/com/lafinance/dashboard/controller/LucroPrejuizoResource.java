package com.lafinance.dashboard.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.LucroPrejuizoDTO;
import com.lafinance.dashboard.service.LucroPrejuizoService;

@CrossOrigin
@RestController
@RequestMapping("/api/lucroPrejuizo")
public class LucroPrejuizoResource {
	
	private final Logger log = LoggerFactory.getLogger(LucroPrejuizoResource.class);
	
	@Autowired
	private LucroPrejuizoService lucroPrejuizo;
	
	@CrossOrigin
	@GetMapping("/calculo/indice/{valorAtual}/{quantidade}/{valorBrutoPago}")
	public ResponseEntity<LucroPrejuizoDTO> calcularIndiceLucroPrejuizo(
			@PathVariable(name = "valorAtual") BigDecimal valorAtual,
			@PathVariable(name = "quantidade") Integer quantidade,
			@PathVariable(name = "valorBrutoPago") String valorBrutoPago) {
		log.debug("API - Calcular percentual índice lucro ou prejuízo");
		try {
			return ResponseEntity.ok().body(lucroPrejuizo.calcularIndiceLucroPrejuizo(valorAtual, quantidade, valorBrutoPago));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@CrossOrigin
	@GetMapping("/calculo/valor/{valorAtual}/{quantidade}/{valorBrutoPago}")
	public ResponseEntity<LucroPrejuizoDTO> calcularValorLucroPrejuizo(
			@PathVariable(name = "valorAtual") BigDecimal valorAtual,
			@PathVariable(name = "quantidade") Integer quantidade,
			@PathVariable(name = "valorBrutoPago") String valorBrutoPago) {
		log.debug("API - Calcular valor lucro ou prejuízo");
		try {
			return ResponseEntity.ok().body(lucroPrejuizo.calcularValorLucroPrejuizo(valorAtual, quantidade, valorBrutoPago));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
