package com.example.primeservice2.controller;

import com.example.primeservice2.model.Customer;
import com.example.primeservice2.service.IAuthenticationService;
import com.example.primeservice2.service.TokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin({"http://localhost:3000"})
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationController(IAuthenticationService authenticationService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenService= tokenService;
    }

    @PostMapping("/register")
    public boolean register (@RequestBody Customer customer) {
        try {
            return authenticationService.register(customer);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public String Login(@RequestBody Customer customer) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(),
                customer.getPassword()));

        return tokenService.generateToken(authentication);
    }
}