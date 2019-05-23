package com.dawid.exchangechallenge.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("application")
public class ApplicationProperties {
    private String currenciesListUrl;
}
