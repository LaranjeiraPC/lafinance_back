package com.lafinance.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lafinance.dashboard.service.ConfiguracaoService;

@CrossOrigin
@RestController
@RequestMapping("/api/configuracao")
public class ConfiguracaoResource {

	@Autowired
	private ConfiguracaoService configuracaoService;

	
}
