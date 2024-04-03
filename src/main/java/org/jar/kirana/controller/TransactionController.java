package org.jar.kirana.controller;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.responses.ApiResponse;
import org.jar.kirana.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    public ResponseEntity<ApiResponse> TransactionCredit(@RequestBody TransactionDto transactionDto, @AuthenticationPrincipal Jwt jwt){
        ApiResponse transactionResult = transactionService.newCredit(transactionDto, jwt);
        return new ResponseEntity<ApiResponse>(transactionResult, HttpStatus.ACCEPTED);
    }

    @PostMapping("/debit")
    public ResponseEntity<ApiResponse> TransactionDebit(@RequestBody TransactionDto transactionDto, @AuthenticationPrincipal Jwt jwt) {
        ApiResponse transactionResult = transactionService.newDebit(transactionDto, jwt);
        return new ResponseEntity<ApiResponse>(transactionResult, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTransactions(@AuthenticationPrincipal Jwt jwt){
        ApiResponse transactionResult = transactionService.getAllTransactions(jwt);
        return new ResponseEntity<ApiResponse>(transactionResult, HttpStatus.ACCEPTED);
    }
}
