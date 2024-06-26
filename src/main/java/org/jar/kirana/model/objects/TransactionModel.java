package org.jar.kirana.model.objects;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class TransactionModel {
    @Id
    private String transactionId;
    private String userId;
    /**
     *     profit - true - Credit
     *     profit - false - debit
     */
    private boolean profit;
    private LocalDateTime transactionTimeStamp;
    /**
     *     value - +ve - Credit
     *     Value - -ve - Debit
     */
    private Double originValue;
    private String originCurrency;
    /**
     * this is the value in terms of USD
     */
    private Double defaultValue;
    private String defaultCurrency;
    public TransactionModel(){
        this.profit = true;
        this.defaultCurrency = "USD";
    }
}