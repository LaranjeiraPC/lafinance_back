package com.lafinance.dashboard.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AlphaVantageConfig {

    @Value("${ALPHA_VANTAGE_URL}")
    public String url;

    @Value("${CHAVE_KEY}")
    public String chave;

    @Value("${ALPHA_VANTAGE_INTERVAL}")
    public String interval;
}
