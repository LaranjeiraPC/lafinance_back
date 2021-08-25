package com.lafinance.dashboard.service;

import java.math.BigDecimal;

import com.lafinance.dashboard.dto.LucroPrejuizoDTO;

public interface LucroPrejuizoService {
	LucroPrejuizoDTO calcularIndiceLucroPrejuizo(BigDecimal valorAtual, Integer quantidade, String valorBrutoPago);
	LucroPrejuizoDTO calcularValorLucroPrejuizo(BigDecimal valorAtual, Integer quantidade, String valorBrutoPago);
}
