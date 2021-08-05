package com.lafinance.dashboard.service;

import java.math.BigDecimal;

import com.lafinance.dashboard.dto.ConfiguracaoDTO;
import com.lafinance.dashboard.util.Response;

public interface ConfiguracaoService {
	ConfiguracaoDTO consultarDadosConfiguracao();
	Response salvarConfiguracao(Object[] dados);
	BigDecimal consultarValorBrutoMeta();
}
