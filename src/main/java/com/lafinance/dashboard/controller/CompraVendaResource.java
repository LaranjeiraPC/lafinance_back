package com.lafinance.dashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.domain.model.CompraVenda;
import com.lafinance.dashboard.service.CompraVendaService;

@CrossOrigin
@RestController
@RequestMapping("/api/compravenda")
public class CompraVendaResource {

    @Autowired
    private CompraVendaService compraVendaService;

    @GetMapping("/search/{id}")
    public ResponseEntity<List<CompraVenda>> consultarDetalhesVenda(@PathVariable(name = "id") String id) {
        try {
            return ResponseEntity.ok().body(compraVendaService.consultarCompraVendaPeloIdVenda(Integer.parseInt(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> salvar(@RequestBody List<CompraVenda> compraVenda) {
        try {
            compraVendaService.salvarRegistro(compraVenda);
            return ResponseEntity.noContent().build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/list/years")
    public ResponseEntity<List<Integer>> listarDataVendaSomenteAno() {
        try {
            return ResponseEntity.ok().body(compraVendaService.listarDataVendaSomenteAno());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list/month/{ano}")
    public ResponseEntity<List<Integer>> listarDataVendaSomenteMes(@PathVariable(name = "ano") Integer ano) {
        try {
            return ResponseEntity.ok().body(compraVendaService.listarDataVendaSomenteMes(ano));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list/ativos")
    public ResponseEntity<List<String>> listarAtivosAgrupandoByNomeAtivo() {
        try {
            return ResponseEntity.ok().body(compraVendaService.listarAtivosAgrupandoByNomeAtivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
