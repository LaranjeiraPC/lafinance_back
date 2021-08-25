package com.lafinance.dashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.dto.DashDTO;
import com.lafinance.dashboard.service.DashService;

@CrossOrigin
@RestController
@RequestMapping("/api/dash")
public class DashResource {
	
	private final Logger log = LoggerFactory.getLogger(DashResource.class);

	private final DashService dashService;

	public DashResource(DashService dashService) {
		this.dashService = dashService;
	}
	
	@CrossOrigin
	@GetMapping("/consultar/")
	public ResponseEntity<DashDTO> consultarDadosDahsboard() {
		log.debug("API - Consultar dados dash");
		try {
			return ResponseEntity.ok().body(dashService.consultarDadosDahsboard());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}