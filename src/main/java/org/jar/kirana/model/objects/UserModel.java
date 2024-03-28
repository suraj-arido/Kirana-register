package org.jar.kirana.model.objects;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "users")
@Data
public class UserModel {
    @Id
    private String userId;
    private String userName;
    private String email;
    private LocalDateTime accountCreationTimeStamp;

    public UserModel(){
        this.userId = UUID.randomUUID().toString();
        this.accountCreationTimeStamp = LocalDateTime.now();
    }
}