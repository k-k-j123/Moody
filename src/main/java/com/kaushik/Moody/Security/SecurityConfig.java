package com.kaushik.Moody.Security;

import com.kaushik.Moody.Model.Users;
import com.kaushik.Moody.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/css/**","/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .successHandler(this::onSuccess)
                );

        return http.build();
    }

    //to store user in the database after authentication
    private void onSuccess(HttpServletRequest request, HttpServletResponse response,
                           org.springframework.security.core.Authentication auth) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) auth;
        Map<String, Object> attributes = token.getPrincipal().getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        if (userService.getUserByEmail(email) == null) {
            Users user = new Users();
            user.setEmail(email);
            user.setName(name);
            userService.createUser(user);
        }

        response.sendRedirect("/home");
    }
}
