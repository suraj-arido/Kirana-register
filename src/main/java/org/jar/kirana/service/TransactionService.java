package org.jar.kirana.service;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.mapper.TransactionMapper;
import org.jar.kirana.model.responses.TransactionApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionDto transactionDto;
    private final
    @Autowired
    public TransactionService(TransactionMapper transactionMapper, TransactionDto transactionDto) {
        this.transactionMapper = transactionMapper;
        this.transactionDto = transactionDto;
    }
    public TransactionApiResponse newCredit(TransactionDto transactionDto){

    }
}