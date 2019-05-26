package com.dawid.exchangechallenge.data;

import lombok.*;

import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDTO {

    private boolean success;
    private long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "success=" + success +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
