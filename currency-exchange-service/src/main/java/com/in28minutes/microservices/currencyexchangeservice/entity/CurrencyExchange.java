package com.in28minutes.microservices.currencyexchangeservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="currency_exchange")
@NoArgsConstructor
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String from;
    private String to;
    private Integer conversionMultiple;
    private String environment;

    // custom constructor
    public CurrencyExchange(Long id, String from, String to, Integer conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }
}
