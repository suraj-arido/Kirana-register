package org.jar.kirana.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak .admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

//    @Value("${keycloak.auth-server-url}")
//    private String serverUrl;
//
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Value("${keycloak.clientId")
//    private String clientId;
//
//    @Value("${keycloak.credentials.clientSecret")
//    private String clientSecret;
//
//    @Value("${keycloak.credentials.username")
//    private String username;
//
//    @Value("${keycloak.credentials.password")
//    private String password;

    @Bean
    public Keycloak getKeycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8090")
                .realm("KiranaRealm")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("kirana_user_creation")
                .clientSecret("XhU3TrRSrqomu19tXnXgY4OoBSa6aTmU")
                .username("admin")
                .password("admin")
                .build();
    }
}

