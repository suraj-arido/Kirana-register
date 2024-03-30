package org.jar.kirana.controller;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.responses.ApiResponse;
import org.jar.kirana.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    public ResponseEntity<ApiResponse> TransactionCredit(@RequestBody TransactionDto transactionDto){
        ApiResponse transactionResult = transactionService.newCredit(transactionDto);
        return new ResponseEntity<ApiResponse>(transactionResult, HttpStatus.ACCEPTED);
    }

    @PostMapping("/debit")
    public ResponseEntity<ApiResponse> TransactionDebit(@RequestBody TransactionDto transactionDto) {
        ApiResponse transactionResult = transactionService.newDebit(transactionDto);
        return new ResponseEntity<ApiResponse>(transactionResult, HttpStatus.ACCEPTED);
    }
}
