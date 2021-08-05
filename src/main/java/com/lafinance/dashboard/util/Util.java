package com.lafinance.dashboard.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Util {

	public static LocalDate montarData(String data) {

		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6, 10);

		return LocalDate.parse(ano + "-" + mes + "-" + dia);
	}

	public static Integer montarAno(LocalDate data) {
		return data.getYear();
	}

	public static String montarMes(LocalDate data) {
		Integer numeroMes = data.getMonth().getValue();

		switch (numeroMes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		default:
			return "";
		}
	}

	public static String converterNomeMesParaInteiro(String mes) {
		switch (mes) {
		case "Janeiro":
			return "01";
		case "Fevereiro":
			return "02";
		case "Março":
			return "03";
		case "Abril":
			return "04";
		case "Maio":
			return "05";
		case "Junho":
			return "06";
		case "Julho":
			return "07";
		case "Agosto":
			return "08";
		case "Setembro":
			return "09";
		case "Outubro":
			return "10";
		case "Novembro":
			return "11";
		case "Dezembro":
			return "12";
		default:
			return "0";
		}
	}

	public static BigDecimal converterParaBigDecimal(String valor) {
		return new BigDecimal(valor.replace(".", "").replace(",", "."));
	}

	public static BigDecimal converterParaBigDecimalSemReplace(String valor) {
		return new BigDecimal(valor);
	}

	private Util() {
		throw new IllegalStateException("Utility class");
	}

}
