package com.lafinance.dashboard.controller;

import com.lafinance.dashboard.service.AnaliseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/api/analise")
public class AnaliseResource {

    private final Logger log = LoggerFactory.getLogger(AnaliseResource.class);

    private final AnaliseService analiseService;

    public AnaliseResource(AnaliseService analiseService) {
        this.analiseService = analiseService;
    }

    @CrossOrigin
    @GetMapping("/calculo/{idVenda}")
    public ResponseEntity<BigDecimal> calcularLucroBruto(
            @PathVariable(name = "idVenda") Integer idVenda) {
        log.debug("API - Consultar compras vendas pelo id");
        try {
            return ResponseEntity.ok().body(analiseService.calcularLucroBruto(idVenda));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
