package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.model.Acao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.util.Response;

@CrossOrigin
@RestController
@RequestMapping("/api/acao")
public class AcaoResource {

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
        try {
            acaoService.excluirAcao(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @GetMapping("/consulta/acoes/venda/{nome}")
    public ResponseEntity<List<AcaoDTO>> consultarAcoesAtivosVenda(@PathVariable(name = "nome") String nome) {
        try {
            return ResponseEntity.ok().body(acaoService.consultarAcoesAtivosVenda(nome));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PostMapping("/inativar/")
    public ResponseEntity<Response> inativarAcoes(@RequestBody List<Acao> acao) {
        try {
            return ResponseEntity.ok().body(acaoService.inativarAcoes(acao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @GetMapping("/atualizar")
    public ResponseEntity<Response> atualizarRegistroAtivos() {
        try {
            return ResponseEntity.ok().body(acaoService.atualizarPrecoAtual());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
