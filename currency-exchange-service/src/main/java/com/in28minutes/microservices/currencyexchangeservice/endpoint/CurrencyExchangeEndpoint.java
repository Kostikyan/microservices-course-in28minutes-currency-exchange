package com.in28minutes.microservices.currencyexchangeservice.endpoint;

import com.in28minutes.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
@RequiredArgsConstructor
public class CurrencyExchangeEndpoint {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange exchangeValue(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    ){
        CurrencyExchange currencyExchange = currencyExchangeService.findByFromAndTo(from, to);

        String port = currencyExchangeService.getEnvLocalPort();
        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }

}
