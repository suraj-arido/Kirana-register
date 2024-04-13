package org.jar.kirana.model.responses;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private String date;
    private String base;
    private Map<String, Double> rates;
}
