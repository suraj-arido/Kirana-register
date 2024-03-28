package org.jar.kirana.model.responses;

import lombok.Data;

@Data
public class UserApiResponse {
    private boolean success;
    private Object data;
    private Object view;
    private String status;
    private String error;
    private Object errorMessage;
    private String errorCode;
    private String displayMsg;

    public UserApiResponse(){
        this.success = true;
    }
}
