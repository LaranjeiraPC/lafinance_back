package com.lafinance.dashboard.controller;

import java.util.List;

import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lafinance.dashboard.domain.dto.AtivoDTO;
import com.lafinance.dashboard.service.AtivoService;

@CrossOrigin
@RestController
@RequestMapping("/api/ativo")
public class AtivoResource {

    @Autowired
    private AtivoService ativoService;

    @PostMapping("/save")
    public ResponseEntity<AtivoDTO> cadastrarAtivo(@RequestBody AtivoDTO ativoDTO) {
        try {
            return ResponseEntity.ok().body(ativoService.salvarAtivo(ativoDTO));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editarAtivo(@RequestBody AtivoDTO ativoDTO) {
        try {
            ativoService.editarAtivo(ativoDTO);
            return ResponseEntity.noContent().build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAtivo(@PathVariable(name = "id") Integer id) {
        try {
            ativoService.excluirAtivo(id);
            return ResponseEntity.noContent().build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.badRequest().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<AtivoDTO>> listarAtivo() {
        try {
            return ResponseEntity.ok().body(ativoService.listarAtivo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search/{nome}")
    public ResponseEntity<AtivoDTO> consultarAtivoByNome(@PathVariable(name = "nome") String nome) {
        try {
            return ResponseEntity.ok().body(ativoService.consultarNomeAtivo(nome));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (NenhumRegistroEncontradoException e) {
            return ResponseEntity.notFound().header(HttpStatus.NOT_FOUND.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
