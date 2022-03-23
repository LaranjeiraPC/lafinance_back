package com.lafinance.dashboard.controller;

import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.service.AlphaVantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin
@RestController
@RequestMapping("/api/alpha_vantage")
public class AlphaVantageResource {

    @Autowired
    private AlphaVantageService alphaVantageService;

    @GetMapping("/refresh")
    public ResponseEntity<BigDecimal> atualizarUltimaCotacao() {
        try {
            this.alphaVantageService.atualizarUltimaCotacao();
            return ResponseEntity.noContent().build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().header(HttpStatus.BAD_REQUEST.toString(), e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
