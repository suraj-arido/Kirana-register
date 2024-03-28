package org.jar.kirana.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private long customerId;
    private LocalDateTime transactionTimeStamp;
    private long value;
    private String currency;
}