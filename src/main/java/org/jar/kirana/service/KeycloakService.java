package org.jar.kirana.service;

import com.sun.nio.sctp.ShutdownNotification;
import jakarta.ws.rs.core.Response;
import org.jar.kirana.dto.UserDto;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KeycloakService {

    private final Keycloak keycloak;
    private final String realm = "KiranaRealm";
    @Autowired
    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public Response createKeycloakUser(UserDto userDto){
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setEmailVerified(true);
//        user.setAttributes(Collections.singletonMap("userId", List.of(mongodbUserId)));
//        System.out.println(user.getAttributes().toString());

//      get realm
        RealmResource realmResource = keycloak.realm(realm);
        System.out.println(realmResource);
        UsersResource userResource = realmResource.users();
        System.out.println(userResource);

//        create the user
        Response response = userResource.create(user);
        System.out.println("Response: " + response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

//        setting role
        RoleRepresentation roleRepresentation = realmResource.roles().get("app_user").toRepresentation();
        userResource.get(userId).roles().realmLevel().add(Collections.singletonList(roleRepresentation));

//        Setting password
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getUserPassword());
        userResource.get(userId).resetPassword(passwordCred);
        return response;
    }
    private void assignRealmRole(String username, String roleName) {
        RealmResource realmResource = keycloak.realm(realm);
        UserResource userResource = realmResource.users().get(username);

        if (userResource != null) {
            userResource.roles().realmLevel().add(Collections.singletonList(realmResource.roles().get(roleName).toRepresentation()));
        } else {
            throw new RuntimeException("User not found: " + username);
        }
    }
}
