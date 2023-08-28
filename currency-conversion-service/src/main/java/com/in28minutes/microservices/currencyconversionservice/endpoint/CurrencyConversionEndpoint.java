package com.in28minutes.microservices.currencyconversionservice.endpoint;

import com.in28minutes.microservices.currencyconversionservice.entity.CurrencyConversion;
import com.in28minutes.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionEndpoint {

    private final CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") int quantity
    ){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        String restTemplateUrl = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

        ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity(restTemplateUrl, CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = forEntity.getBody();
        return new CurrencyConversion(10001L,
                from, to, quantity,
                currencyConversion.getConversionMultiple(),
                (quantity * currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " rest template");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") int quantity
    ){

        CurrencyConversion currencyConversion = proxy.exchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(),
                from, to, quantity,
                currencyConversion.getConversionMultiple(),
                (quantity * currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " feign");
    }
}
