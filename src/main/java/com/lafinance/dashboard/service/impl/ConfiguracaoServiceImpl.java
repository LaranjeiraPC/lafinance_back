package com.lafinance.dashboard.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.repository.ConfiguracaoRepository;
import com.lafinance.dashboard.service.ConfiguracaoService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ConfiguracaoServiceImpl implements ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;


}
