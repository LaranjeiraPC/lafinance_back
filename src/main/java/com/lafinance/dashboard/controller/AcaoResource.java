package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Ativo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.util.Response;

import javax.ws.rs.DELETE;

@CrossOrigin
@RestController
@RequestMapping("/api/acao")
public class AcaoResource {

    private final Logger log = LoggerFactory.getLogger(AcaoResource.class);

    private final AcaoService acaoService;

    public AcaoResource(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @CrossOrigin
    @GetMapping("/consulta/acoes/")
    public ResponseEntity<List<AcaoDTO>> consultarAcoesAtivos() {
        try {
            return ResponseEntity.ok().body(acaoService.consultarAcoesAtivos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PostMapping("/salva/")
    public ResponseEntity<Response> cadastrarAcao(@RequestBody Acao acao) {
        try {
            return ResponseEntity.ok().body(acaoService.cadastrarAcao(acao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PostMapping("/edita/")
    public ResponseEntity<Response> editarAcao(@RequestBody AcaoDTO acao) {
        try {
            return ResponseEntity.ok().body(acaoService.editarAcao(acao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Response> excluirAcao(@PathVariable(name = "id") Integer id) {

        log.debug("API - excluir Acao");

        try {
            return ResponseEntity.ok().body(acaoService.excluirAcao(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
