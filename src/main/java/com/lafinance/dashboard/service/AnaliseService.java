package com.lafinance.dashboard.service;

import java.math.BigDecimal;

public interface AnaliseService {
    BigDecimal calcularLucroBruto(Integer id) throws Exception;
}
