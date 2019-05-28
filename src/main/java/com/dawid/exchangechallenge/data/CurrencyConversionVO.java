package com.dawid.exchangechallenge.data;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyConversionVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 3, min = 3)
    private String sourceCurrency;

    @NotNull
    private Double amountToConvert;

    @Size(max = 3, min = 3)
    private String targetCurrency;

    @NotNull
    private Double convertedAmount;

}
