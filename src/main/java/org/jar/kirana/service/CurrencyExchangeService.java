package org.jar.kirana.service;

import org.jar.kirana.model.responses.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeService {
    private final RestTemplate restTemplate;
    private final ExchangeRateResponse exchangeRateResponse;

    @Autowired
    public CurrencyExchangeService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.exchangeRateResponse = getExchangeRates();
    }
    ExchangeRateResponse getExchangeRates(){
        return restTemplate.getForObject("https://api.fxratesapi.com/latest", ExchangeRateResponse.class);
    }

    public Double getUsdValue(String currency, Double value){
        Double usdEquivalent = this.exchangeRateResponse.getRates().get(currency);
        return (value / usdEquivalent);
    }
}