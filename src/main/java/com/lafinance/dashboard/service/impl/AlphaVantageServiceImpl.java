package com.lafinance.dashboard.service.impl;

import com.lafinance.dashboard.infrastructure.api.AlphaVantageAPI;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AlphaVantageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AlphaVantageServiceImpl implements AlphaVantageService {

    @Autowired
    private AlphaVantageAPI alphaVantageAPI;

    @Autowired
    private AcaoService acaoService;

    public void atualizarUltimaCotacao() throws Exception {
        List<String> acaoDTO = this.acaoService.listarAtivosAgrupandoByNomeAtivo();
        this.acaoService.atualizarUltimaCotacao(this.alphaVantageAPI.consultarUltimaCotacao(acaoDTO));
    }
}
