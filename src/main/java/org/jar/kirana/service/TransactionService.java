package org.jar.kirana.service;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.mapper.TransactionMapper;
import org.jar.kirana.model.objects.TransactionModel;
import org.jar.kirana.model.responses.ApiResponse;
import org.jar.kirana.resository.TransactionRepository;
import org.jar.kirana.validator.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.jwt.Jwt;


import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionValidator transactionValidator;

    @Autowired
    public TransactionService(TransactionMapper transactionMapper, TransactionRepository transactionRepository, TransactionValidator transactionValidator) {
        this.transactionMapper = transactionMapper;
        this.transactionValidator = transactionValidator;
        this.transactionRepository = transactionRepository;
    }

    public ApiResponse newCredit(TransactionDto transactionDto, Jwt jwt) {
        ApiResponse response = new ApiResponse();
        try {
            transactionValidator.creditValidate(transactionDto);
            TransactionModel transaction = transactionMapper.toTransaction(transactionDto);
            transaction.setUserId(jwt.getClaim("sub").toString());
            TransactionModel savedTransaction = transactionRepository.save(transaction);
            TransactionDto savedTransactionDto = transactionMapper.toDto(savedTransaction);
            response.setData(savedTransactionDto);
            response.setStatus("201");
            response.setDisplayMsg(MessageFormat.format("Thank You {0}, your credit of {1} {2} is Successfully completed.", jwt.getClaim("preferred_username").toString(), savedTransactionDto.getValue(), savedTransactionDto.getCurrency()));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setStatus("409");
            response.setErrorMessage(e.getMessage());
            response.setDisplayMsg(e.getLocalizedMessage());
        }
        return response;
    }

    public ApiResponse newDebit(TransactionDto transactionDto, Jwt jwt) {
        ApiResponse response = new ApiResponse();
        try {
            transactionDto.setValue(-transactionDto.getValue());
            transactionValidator.debitValidator(transactionDto);
            TransactionModel transaction = transactionMapper.toTransaction(transactionDto);
            transaction.setUserId(jwt.getClaim("sub").toString());
            TransactionModel savedTransaction = transactionRepository.save(transaction);
            TransactionDto savedTransactionDto = transactionMapper.toDto(savedTransaction);
            response.setData(savedTransactionDto);
            response.setStatus("201");
            response.setDisplayMsg(MessageFormat.format("Thank You {0}, your Debit of {1} {2} is Successfully completed.", jwt.getClaim("preferred_username"), savedTransactionDto.getValue(), savedTransactionDto.getCurrency()));
        } catch (Exception e) {
            response.setSuccess(false);
            response.setStatus("409");
            response.setErrorMessage(e.getMessage());
            response.setDisplayMsg(e.getLocalizedMessage());
        }
        return response;
    }

    public ApiResponse getAllTransactions(Jwt jwt) {
        ApiResponse response = new ApiResponse();
        List<TransactionModel> transactionModels = transactionRepository.findByUserId(jwt.getClaim("sub").toString());
        List<TransactionDto> transactionDtos = transactionModels.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
        response.setData(transactionDtos);
        return response;
    }

}