package org.jar.kirana.model.objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
@NoArgsConstructor
public class TransactionModel {
    @Id
    private long transactionId;
    private long userId;
    private LocalDateTime transactionTimeStamp;
    private long value;
    private String currency;
}