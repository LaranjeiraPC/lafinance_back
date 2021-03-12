package com.lafinance.dashboard.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class MessageUtil {

	private static final String ARQUIVO = "message";
	private static ResourceBundle bundle = ResourceBundle.getBundle(ARQUIVO);

	private MessageUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String getMessage(String code) {
		return bundle.getString(code);
	}

	public static String getMessage(String code, Object... parametros) {
		return MessageFormat.format(bundle.getString(code), parametros);
	}
	
}
