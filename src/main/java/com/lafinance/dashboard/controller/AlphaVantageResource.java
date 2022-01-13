package com.lafinance.dashboard.controller;

import com.lafinance.dashboard.service.AlphaVantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/api/alpha_vantage")
public class AlphaVantageResource {

    @Autowired
    private AlphaVantageService alphaVantageService;

    @CrossOrigin
    @GetMapping("/ativo/{nome}")
    public ResponseEntity<BigDecimal> consultarAcoesAtivos(@PathVariable(name = "nome") String nome) {
        try {
            return ResponseEntity.ok().body(alphaVantageService.consultarPrecoAlvo(nome));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
