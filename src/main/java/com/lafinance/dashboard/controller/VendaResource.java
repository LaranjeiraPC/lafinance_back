package com.lafinance.dashboard.controller;

import java.math.BigDecimal;
import java.util.List;

import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.domain.dto.VendaDTO;
import com.lafinance.dashboard.service.VendaService;

@CrossOrigin
@RestController
@RequestMapping("/api/venda")
public class VendaResource {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/save")
    public ResponseEntity<VendaDTO> salvarVenda(@RequestBody VendaDTO vendaDTO) {
        try {
            return ResponseEntity.ok().body(this.vendaService.cadastrar(vendaDTO));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search/ano/mes/{ano}/{mes}")
    public ResponseEntity<List<VendaDTO>> consultarVendasPeloMesSelecionado(@PathVariable(name = "ano") String ano,
                                                                            @PathVariable(name = "mes") String mes) {
        try {
            return ResponseEntity.ok().body(this.vendaService.consultarVendasPeloAnoMesSelecionado(ano, mes));
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable(name = "id") Integer id) {
        try {
            this.vendaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editarVenda(
            @RequestBody VendaDTO venda) {
        try {
            this.vendaService.editar(venda);
            return ResponseEntity.noContent().build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/calc/{idVenda}")
    public ResponseEntity<BigDecimal> calcularLucroBruto(
            @PathVariable(name = "idVenda") Integer idVenda) {
        try {
            return ResponseEntity.ok().body(this.vendaService.calcularLucroBruto(idVenda));
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
