package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.domain.dto.AcaoDTO;
import com.lafinance.dashboard.service.AcaoService;

@CrossOrigin
@RestController
@RequestMapping("/api/acao")
public class AcaoResource {

    @Autowired
    private AcaoService acaoService;

    @PostMapping("/save")
    public ResponseEntity<AcaoDTO> cadastrarAcao(@RequestBody AcaoDTO acaoDTO) {
        try {
            return ResponseEntity.ok().body(acaoService.cadastrarAcao(acaoDTO));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editarAcao(@RequestBody AcaoDTO acaoDTO) {
        try {
            this.acaoService.editarAcao(acaoDTO);
            return ResponseEntity.noContent().build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAcao(@PathVariable(name = "id") Integer id) {
        try {
            acaoService.excluirAcao(id);
            return ResponseEntity.noContent().build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search/acoes/venda/{nome}")
    public ResponseEntity<List<AcaoDTO>> listarAcoesAtivosVenda(@PathVariable(name = "nome") String nome) {
        try {
            return ResponseEntity.ok().body(acaoService.listarAcoesAtivosVenda(nome));
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/disable")
    public ResponseEntity<Void> inativarAcoes(@RequestBody List<AcaoDTO> acaoDTO) {
        try {
            acaoService.inativarAcoes(acaoDTO);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search/acoes/atual/{mes}/{ano}")
    public ResponseEntity<List<AcaoDTO>> listarAcoesAtivosMesCorrente(
            @PathVariable(name = "mes") Integer mes,
            @PathVariable(name = "ano") Integer ano) {
        try {
            return ResponseEntity.ok().body(acaoService.listarAcoesAtivosMesCorrente(mes, ano));
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/search/acoes/outros")
    public ResponseEntity<List<AcaoDTO>> listarAcoesAtivosOutrosMeses(
            @RequestBody List<Acao> ids
    ) {
        try {
            return ResponseEntity.ok().body(acaoService.listarAcoesAtivosOutrosMeses(ids));
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<AcaoDTO>> listarAcoesAtivos() {
        try {
            return ResponseEntity.ok().body(acaoService.listarAcoesAtivos());
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
