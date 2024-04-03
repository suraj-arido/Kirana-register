package org.jar.kirana.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
//    private final KeycloakLogoutHandler keycloakLogoutHandler;

    private final JwtConverter jwtConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) ->
                auth
                        .requestMatchers("/test/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/test/hello").permitAll()
                        .requestMatchers(HttpMethod.GET, "/test/admin/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/test/user/**").hasRole(USER)
                        .requestMatchers(HttpMethod.POST, "/user/newUser").permitAll()
                        .requestMatchers(HttpMethod.POST, "/transaction/credit").hasRole(USER)
                        .requestMatchers(HttpMethod.GET, "/api/admin-and-user/**").hasAnyRole(ADMIN,USER)
                        .anyRequest().authenticated());

        http.sessionManagement(session -> session.
                sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.
                        jwtAuthenticationConverter(jwtConverter)));
//        logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/"));
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}