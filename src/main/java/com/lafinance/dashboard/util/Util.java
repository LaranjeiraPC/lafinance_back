package com.lafinance.dashboard.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Util {
	
	public static LocalDate montarData(String data) {
		
		String dia = data.substring(0,2);
		String mes = data.substring(3,5);
		String ano = data.substring(6,10);
		
		return LocalDate.parse(ano + "-" + mes + "-" + dia);
	}
	
	public static BigDecimal converterParaBigDecimal(String valor) {
		return new BigDecimal(valor.replace(".", "").replace(",", "."));
	}
	
	private Util() {
		throw new IllegalStateException("Utility class");
	}

}
