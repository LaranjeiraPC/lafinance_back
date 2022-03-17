package com.lafinance.dashboard.service;

import java.math.BigDecimal;

public interface AlphaVantageService {

    BigDecimal consultarPrecoAlvo(String ativo);

}
