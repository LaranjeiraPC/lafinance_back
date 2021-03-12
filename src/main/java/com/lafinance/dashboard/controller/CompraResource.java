package com.lafinance.dashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.service.CompraService;

@CrossOrigin
@RestController
@RequestMapping("/api/compra")
public class CompraResource {

	private final Logger log = LoggerFactory.getLogger(CompraResource.class);

	private final CompraService compraService;

	public CompraResource(CompraService compraService) {
		this.compraService = compraService;
	}

}
