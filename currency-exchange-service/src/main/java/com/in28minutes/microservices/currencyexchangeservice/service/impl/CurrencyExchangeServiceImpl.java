package com.in28minutes.microservices.currencyexchangeservice.service.impl;

import com.in28minutes.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import com.in28minutes.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Environment environment;

    @Override
    public List<CurrencyExchange> findAll() {
        return currencyExchangeRepository.findAll();
    }

    @Override
    public String getEnvLocalPort() {
        return environment.getProperty("local.server.port");
    }

    @Override
    public CurrencyExchange findByFromAndTo(String from, String to) {
        Optional<CurrencyExchange> byFromAndTo = currencyExchangeRepository.findByFromAndTo(from, to);
        if (byFromAndTo.isEmpty()) throw new RuntimeException("Unable to find data for " + from + " to " + to);
        return byFromAndTo.get();
    }
}
