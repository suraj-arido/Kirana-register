package org.jar.kirana.validator;

import org.jar.kirana.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class TransactionValidator {
    private boolean currencyIsValid(TransactionDto transaction) {
        Set<String> currency = new HashSet<>(Arrays.asList("USD", "INR"));
        return currency.contains(transaction.getCurrency());
    }
    private boolean valueGreaterThanZero(TransactionDto transaction){
        return transaction.getValue() > 0;
    }
    private boolean valueLessThanZero(TransactionDto transaction){
        return transaction.getValue() < 0;
    }
    private void validateCurrency(TransactionDto transaction){
        if(!currencyIsValid(transaction)) {
            throw new InvalidParameterException("The following currency is not accepted");
        }
    }
    public void creditValidate(TransactionDto transaction){
        validateCurrency(transaction);
        if(!valueGreaterThanZero(transaction)){
            throw new InvalidParameterException("The value to be credited to less that 0, it should greater than 0");
        }
    }
    public void debitValidator(TransactionDto transaction){
        validateCurrency(transaction);
        if(!valueLessThanZero(transaction)){
            throw new InvalidParameterException("The value being enter to debit is less 0, it should be greater than 0");
        }
    }
}
