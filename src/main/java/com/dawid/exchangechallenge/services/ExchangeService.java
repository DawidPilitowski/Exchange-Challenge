package com.dawid.exchangechallenge.services;

import com.dawid.exchangechallenge.Controller.ExchangeController;
import com.dawid.exchangechallenge.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
@Slf4j
public class ExchangeService {
    @Autowired
    private ExchangeController exchangeController;
    @Autowired
    @Qualifier("defaultRestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private ApplicationProperties properties;

    public List<Double> getChangeCurriences() {
        String from = "USD";
        String to = "EUR";
        URI uri = UriComponentsBuilder
                .fromUriString("http://rate-exchange-1.appspot.com/currency?from=" + from + "&to=" + to)
                .build()
                .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(uri, Map.class);
        Map<String, Double> body = responseEntity.getBody();

        List<Double> rate = new ArrayList<>(body.values());
        return rate;
    }

    public Set<String> getCurrenciesList() {
        URI uri = UriComponentsBuilder
                .fromUriString(properties.getCurrenciesListUrl())
                .build()
                .toUri();

        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(uri, Map.class);
        Map<String, String> body = responseEntity.getBody();
        if (body != null) {
            return body.keySet();
        }
        log.info("Currencies list are empty!");
        return Collections.emptySet();
    }

    public List getKeyList() {
        List<String> keyList = new ArrayList(getCurrenciesList());
        return keyList;
    }
}