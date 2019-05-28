package com.dawid.exchangechallenge.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrienciesDTO {
    private String sourceCurrency;
    private Double amountToConvert;
    private String targetCurrency;
    private Double convertedAmount;
}
