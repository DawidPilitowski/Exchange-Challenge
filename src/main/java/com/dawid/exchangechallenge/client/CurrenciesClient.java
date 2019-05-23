package com.dawid.exchangechallenge.client;

import com.dawid.exchangechallenge.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class CurrenciesClient {

    @Autowired
    @Qualifier("defaultRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties properties;

    public Set<String> getCurrenciesList() {
        URI uri = UriComponentsBuilder
                .fromUriString(properties.getCurrenciesListUrl())
                .build()
                .toUri();

        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(uri, Map.class);

        Map<String, String> body = responseEntity.getBody();
        ArrayList<String> keyList = new ArrayList<String>(body.keySet());
        for (int i = 0; i < keyList.size(); i++) {
            System.out.println(keyList.get(i));
        }
        if (body != null) {
            return body.keySet();
        }

        log.info("Currencies list are empty!");
        return Collections.emptySet();
    }
}
