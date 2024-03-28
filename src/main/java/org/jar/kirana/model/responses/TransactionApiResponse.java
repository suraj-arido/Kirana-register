package org.jar.kirana.model.responses;

import lombok.Data;

@Data
public class TransactionApiResponse {
    private Object data;
    private String error;
    private String errorMessage;

}
