package org.jar.kirana.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private long value;
    private String currency;
    private LocalDateTime transactionTimeStamp;

    public TransactionDto(){
        this.transactionTimeStamp = LocalDateTime.now();
    }
}