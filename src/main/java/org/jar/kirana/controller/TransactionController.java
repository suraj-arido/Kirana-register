package org.jar.kirana.controller;

import org.jar.kirana.dto.TransactionDto;
import org.jar.kirana.model.responses.TransactionApiResponse;
import org.jar.kirana.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    public ResponseEntity<TransactionApiResponse> TransactionCredit(@ResponseBody TransactionDto transactionDto){
        TransactionApiResponse transactionResult = TransactionService.newCredit(transactionDto);
        return new ResponseEntity<TransactionApiResponse>(transactionResult, HttpStatus.OK);
    }

}
