package com.dawid.exchangechallenge.loader;

import com.dawid.exchangechallenge.data.CurrenciesNamesStore;
import com.dawid.exchangechallenge.services.ExchangeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
@Slf4j
public class CurrenciesNamesLoader {

    @Autowired
    private ExchangeService currenciesClient;

    @PostConstruct
    private void loadCurrenciesList() {
        log.info("Start loading currencies list...");
        CurrenciesNamesStore.CURRENCIES_NAMES = currenciesClient.getCurrenciesList();
        log.info("Finish loading currencies names: {}", CurrenciesNamesStore.CURRENCIES_NAMES.toString());
    }
}
