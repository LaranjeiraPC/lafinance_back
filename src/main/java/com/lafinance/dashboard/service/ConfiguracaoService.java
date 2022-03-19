package com.lafinance.dashboard.service;

import com.lafinance.dashboard.domain.dto.ConfiguracaoDTO;
import com.lafinance.dashboard.util.Response;

public interface ConfiguracaoService {
	ConfiguracaoDTO consultarDadosConfiguracao();
	Response salvarConfiguracao(Object[] dados);
}
