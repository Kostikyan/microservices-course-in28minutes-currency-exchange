package com.in28minutes.microservices.currencyconversionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private Integer quantity;
    private Integer conversionMultiple;
    private Integer totalCalculatedAmount;
    private String environment;
}
