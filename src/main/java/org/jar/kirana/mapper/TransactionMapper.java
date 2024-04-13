package org.jar.kirana.mapper;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.objects.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto toDto(TransactionModel transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionTimeStamp(transaction.getTransactionTimeStamp());
        transactionDto.setValue(transaction.getOriginValue());
        transactionDto.setCurrency(transaction.getOriginCurrency());
        return transactionDto;
    }
    public TransactionModel toTransaction(TransactionDto transactionDto){
        TransactionModel transaction = new TransactionModel();
        transaction.setTransactionTimeStamp(transactionDto.getTransactionTimeStamp());
        transaction.setOriginCurrency(transactionDto.getCurrency());
        transaction.setOriginValue(transactionDto.getValue());
        return transaction;
    }
}