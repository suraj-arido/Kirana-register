package org.jar.kirana.dto;

import lombok.Data;
@Data
public class TransactionDto {
    private String userId;
    private long value;
    private String currency;
}