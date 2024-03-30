package org.jar.kirana.mapper;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.objects.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto toDto(TransactionModel transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setUserId(transaction.getUserId());
        transactionDto.setValue(transaction.getValue());
        transactionDto.setCurrency(transaction.getCurrency());
        return transactionDto;
    }
    public TransactionModel toTransaction(TransactionDto transactionDto){
        TransactionModel transaction = new TransactionModel();
        transaction.setUserId(transactionDto.getUserId());
        transaction.setValue(transactionDto.getValue());
        transaction.setCurrency(transactionDto.getCurrency());
        return transaction;
    }
}