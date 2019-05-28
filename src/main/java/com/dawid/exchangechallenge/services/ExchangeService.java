package com.dawid.exchangechallenge.services;

import com.dawid.exchangechallenge.Controller.ExchangeController;
import com.dawid.exchangechallenge.data.CurrencyConversionVO;
import com.dawid.exchangechallenge.data.CurrienciesDTO;
import com.dawid.exchangechallenge.properties.ApplicationProperties;
import com.dawid.exchangechallenge.repository.CurrincesRepository;
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
    @Qualifier("defaultRestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private ApplicationProperties properties;
    @Autowired
    private CurrincesRepository currincesRepository;

    public double getChangeCurriences(CurrienciesDTO dto) {
        if (dto.getConvertedAmount() == null) {
            return 0.0;
        } else {
            return dto.getAmountToConvert() * getRate(dto);
        }
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

    public void add(CurrienciesDTO dto) {
        CurrencyConversionVO currencyConversionVO = new CurrencyConversionVO();
        currencyConversionVO.setAmountToConvert(dto.getAmountToConvert());
        currencyConversionVO.setSourceCurrency(dto.getSourceCurrency());
        currencyConversionVO.setTargetCurrency(dto.getTargetCurrency());
        Double a = getRate(dto);
        Double b = dto.getAmountToConvert();
        Double c = a * b;
        currencyConversionVO.setConvertedAmount(c);
        currincesRepository.save(currencyConversionVO);
    }

    private Double getRate(CurrienciesDTO dto) {
        String from = dto.getSourceCurrency();
        String to = dto.getTargetCurrency();
        URI uri = UriComponentsBuilder
                .fromUriString("http://rate-exchange-1.appspot.com/currency?from=" + from + "&to=" + to)
                .build()
                .toUri();
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(uri, Map.class);
        Map<String, Double> body = responseEntity.getBody();
        List<Double> rate = new ArrayList<Double>(body.values());
        return rate.get(1);
    }

    public List<CurrencyConversionVO> listAll(CurrencyConversionVO currencyConversionVO) {
        return currincesRepository.findAll();
    }

    public Optional<CurrencyConversionVO> findById(CurrencyConversionVO currencyConversionVO) {
        List<CurrencyConversionVO> list = listAll(currencyConversionVO);
        Long a = Long.valueOf(list.size() - 1);
        return currincesRepository.findById(a);
    }
}