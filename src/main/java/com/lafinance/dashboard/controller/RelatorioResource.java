package com.lafinance.dashboard.controller;

import com.lafinance.dashboard.domain.dto.RelatorioDTO;
import com.lafinance.dashboard.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/relatorio")
public class RelatorioResource {

    @Autowired
    private RelatorioService relatorioCompraService;

    @GetMapping("/search/{mes}/{ano}")
    public ResponseEntity<List<RelatorioDTO>> consultarDadosPorMes(
            @PathVariable(name = "mes") Integer mes,
            @PathVariable(name = "ano") Integer ano) {
        try {
            return ResponseEntity.ok().body(relatorioCompraService.consultarDadosPorMes(mes, ano));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search/{ano}")
    public ResponseEntity<Map<String, List<RelatorioDTO>>> consultarDadosAno(
            @PathVariable(name = "ano") Integer ano) {
        try {
            return ResponseEntity.ok().body(relatorioCompraService.consultarDadosPorAno(ano));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
