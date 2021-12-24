package com.lafinance.dashboard.service.impl;

import com.lafinance.dashboard.infrastructure.api.AlphaVantageAPI;
import com.lafinance.dashboard.service.AlphaVantageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class AlphaVantageServiceImpl implements AlphaVantageService {

    @Autowired
    private AlphaVantageAPI alphaVantageAPI;

    private static BigDecimal PRECO_ALVO = BigDecimal.ZERO;

    @Override
    public BigDecimal consultarPrecoAlvo(String ativo) {
        new Thread(() -> {
            try {
                PRECO_ALVO = BigDecimal.ZERO;
                PRECO_ALVO = alphaVantageAPI.consultarPrecoAlvo(ativo);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }).start();
        return PRECO_ALVO;
    }

}
