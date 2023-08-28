package com.in28minutes.microservices.currencyexchangeservice.service;

import com.in28minutes.microservices.currencyexchangeservice.entity.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeService {
    List<CurrencyExchange> findAll();

    String getEnvLocalPort();

    CurrencyExchange findByFromAndTo(String from, String to);
}
