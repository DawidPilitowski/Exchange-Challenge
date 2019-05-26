package com.dawid.exchangechallenge.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionVO {

	@NotBlank
	@Size(max = 3, min = 3)
	private String sourceCurrency;

	@NotNull
	@DecimalMin("0.0")
	@Digits(integer = 11, fraction = 2)
	private BigDecimal amountToConvert;

	@NotBlank
	@Size(max = 3, min = 3)
	private String targetCurrency;

	private BigDecimal convertedAmount;


	public CurrencyConversionVO(String fromCurrency, BigDecimal fromAmount, String toCurrency) {
		super();
		this.sourceCurrency = fromCurrency;
		this.amountToConvert = fromAmount;
		this.targetCurrency = toCurrency;
	}

}
