package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.LucroPrejuizoDTO;
import com.lafinance.dashboard.service.LucroPrejuizoService;

@Service
@Transactional
public class LucroPrejuizoServiceImpl implements LucroPrejuizoService{
	
	private final Logger log = LoggerFactory.getLogger(LucroPrejuizoServiceImpl.class);

	@Override
	public LucroPrejuizoDTO calcularIndiceLucroPrejuizo(BigDecimal valorAtual, Integer quantidade,
			String valorBrutoPago) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LucroPrejuizoDTO calcularValorLucroPrejuizo(BigDecimal valorAtual, Integer quantidade,
			String valorBrutoPago) {
		log.debug("Calculando valor lucro ou prejuizo");
		BigDecimal valorBrutoPagoTemp = new BigDecimal(valorBrutoPago);
		
		BigDecimal quantidadeFormatado = new BigDecimal(quantidade.toString());
		BigDecimal valorBrutoAtual = valorAtual.multiply(quantidadeFormatado);
		BigDecimal valorBrutoCalculado = valorBrutoAtual.subtract(valorBrutoPagoTemp);
		log.debug("Valor Bruto Calculado: {}", valorBrutoCalculado);
		return new LucroPrejuizoDTO(null, valorBrutoCalculado);
	}

}
