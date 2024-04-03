package org.jar.kirana.mapper;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.objects.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto toDto(TransactionModel transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setValue(transaction.getValue());
        transactionDto.setCurrency(transaction.getCurrency());
        transactionDto.setTransactionTimeStamp(transaction.getTransactionTimeStamp());
        return transactionDto;
    }
    public TransactionModel toTransaction(TransactionDto transactionDto){
        TransactionModel transaction = new TransactionModel();
        transaction.setValue(transactionDto.getValue());
        transaction.setTransactionTimeStamp(transactionDto.getTransactionTimeStamp());
        transaction.setCurrency(transactionDto.getCurrency());
        return transaction;
    }
}