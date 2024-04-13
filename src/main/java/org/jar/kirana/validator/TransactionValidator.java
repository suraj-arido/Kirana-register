package org.jar.kirana.validator;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.responses.ExchangeRateResponse;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Map;

@Component
public class TransactionValidator {
    private boolean currencyIsValid(TransactionDto transaction, ExchangeRateResponse exchangeRateResponse) {
        Map<String, Double> rates = exchangeRateResponse.getRates();
        return rates.containsKey(transaction.getCurrency());
    }

    private boolean valueGreaterThanZero(TransactionDto transaction) {
        return ((double) transaction.getValue()) > 0;
    }

    private boolean valueLessThanZero(TransactionDto transaction) {
        return ((double) transaction.getValue()) < 0;
    }

    private void validateCurrency(TransactionDto transaction, ExchangeRateResponse exchangeRateResponse) {
        if (!currencyIsValid(transaction, exchangeRateResponse)) {
            throw new InvalidParameterException("The following currency is not accepted");
        }
    }

    public void creditValidate(TransactionDto transaction, ExchangeRateResponse exchangeRateResponse) {
        validateCurrency(transaction, exchangeRateResponse);
        if (!valueGreaterThanZero(transaction)) {
            throw new InvalidParameterException("The value to be credited to less that 0, it should greater than 0");
        }
    }

    public void debitValidator(TransactionDto transaction, ExchangeRateResponse exchangeRateResponse) {
        validateCurrency(transaction, exchangeRateResponse);
        if (!valueLessThanZero(transaction)) {
            throw new InvalidParameterException("The value being enter to debit is less 0, it should be greater than 0");
        }
    }
}
