package com.dryve.fipedryve.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dryve.fipedryve.entity.FipeRequestBody;
import com.dryve.fipedryve.entity.FipeResponse;

@Service
public class FipeService {

    private final RestTemplate restTemplate;

    @Autowired
    public FipeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FipeResponse consultaFipe(FipeRequestBody requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Referer", "http://veiculos.fipe.org.br");
        headers.set("Host", "veiculos.fipe.org.br");
        HttpEntity<FipeRequestBody> requestEntity = new HttpEntity<>(requestBody, headers);
        System.out.println(requestEntity);

        FipeResponse response = restTemplate.exchange(
            "http://veiculos.fipe.org.br/api/veiculos/ConsultarValorComTodosParametros",
            HttpMethod.POST,
            requestEntity,
            FipeResponse.class).getBody();

        return response;
    }

    public int getTabelaReferencia() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Referer", "http://veiculos.fipe.org.br");
        
        HttpEntity<String> entity = new HttpEntity<>("", headers);
    
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
            "http://veiculos.fipe.org.br/api/veiculos/ConsultarTabelaDeReferencia",
            HttpMethod.POST,
            entity,
            new ParameterizedTypeReference<List<Map<String, Object>>>() {});
    
        // assumindo que o primeiro item da lista é o mais recente
        return (Integer) response.getBody().get(0).get("Codigo");
    }
}

