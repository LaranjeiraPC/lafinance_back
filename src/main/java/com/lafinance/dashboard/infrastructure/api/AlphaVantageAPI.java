package com.lafinance.dashboard.infrastructure.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lafinance.dashboard.config.AlphaVantageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlphaVantageAPI {

    public static final String LIMITE__DISPONIBILIDADE_SERVICO = "Limite da requisição atingida. Aguardando disponibilidade do serviço!";

    @Autowired
    private AlphaVantageConfig alphaVantageConfig;

    private static HttpClient httpClient;

    public List<Map<String, BigDecimal>> consultarUltimaCotacao(List<String> ativo) throws Exception {
        try {
            List<Map<String, BigDecimal>> listaAtivoCotacao = new ArrayList<>();

            for (String a : ativo) {
                Map<String, BigDecimal> ativoCotacao = new HashMap<>();
                httpClient = HttpClient.newHttpClient();

                URI uri = URI.create(alphaVantageConfig.url + a + alphaVantageConfig.interval + alphaVantageConfig.chave);

                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(uri)
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                JsonNode mapping = new ObjectMapper().readValue(response.body(), JsonNode.class);

                if (mapping.get("Note") == null) {
                    BigDecimal cotacao = extrairJson(response);
                    if (cotacao != null) {
                        ativoCotacao.put(a, cotacao);
                        listaAtivoCotacao.add(ativoCotacao);
                    }
                } else {
                    HttpResponse<String> refreshResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    JsonNode refreshMapping = new ObjectMapper().readValue(refreshResponse.body(), JsonNode.class);
                    while (refreshMapping.get("Note") != null) {
                        Thread.sleep(60000);
                        refreshResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        refreshMapping = new ObjectMapper().readValue(refreshResponse.body(), JsonNode.class);
                        if (mapping.get("Note") == null) {
                            BigDecimal cotacao = extrairJson(refreshResponse);
                            if (cotacao != null) {
                                ativoCotacao.put(a, cotacao);
                                listaAtivoCotacao.add(ativoCotacao);
                            }
                        }
                    }
                }
            }
            return listaAtivoCotacao;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private BigDecimal extrairJson(HttpResponse<String> response) throws JsonProcessingException {
        var getValueResponse = response.body();

        JsonNode mapping = new ObjectMapper().readValue(getValueResponse, JsonNode.class);

        for (JsonNode node : mapping.get("Time Series (Daily)")) {
            return new BigDecimal(node.get("4. close").asText());
        }
        return null;
    }
}
