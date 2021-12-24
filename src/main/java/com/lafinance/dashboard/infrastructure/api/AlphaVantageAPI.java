package com.lafinance.dashboard.infrastructure.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import com.lafinance.dashboard.config.AlphaVantageConfig;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


@Slf4j
@Service
public class AlphaVantageAPI {

    @Autowired
    private AlphaVantageConfig alphaVantageConfig;

    private static HttpClient httpClient;

    public BigDecimal consultarPrecoAlvo(String ativo) throws Exception {
        log.debug("Consultar último preco do ativo <{}>", ativo);

        try {
            httpClient = HttpClient.newHttpClient();

            URI uri = URI.create(alphaVantageConfig.url + ativo + alphaVantageConfig.interval + alphaVantageConfig.chave);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode mapping = new ObjectMapper().readValue(response.body(), JsonNode.class);

            if (response.statusCode() == 200 && mapping.get("Note") == null) {
                BigDecimal node = extrairJson(response);
                if (node != null) return node;
            }else{
                log.info("Error {}", response.statusCode());
                Thread.sleep(60000);

                HttpResponse<String> refreshResponse = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                BigDecimal node = extrairJson(refreshResponse);
                if (node != null) return node;

            }
            return null;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
