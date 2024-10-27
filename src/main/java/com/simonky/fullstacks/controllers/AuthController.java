package com.simonky.fullstacks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simonky.fullstacks.dtos.LoginRequest;
import com.simonky.fullstacks.dtos.LoginResponse;
import com.simonky.fullstacks.dtos.RegisterUserRequest;
import com.simonky.fullstacks.models.User;
import com.simonky.fullstacks.security.JwtService;
import com.simonky.fullstacks.services.AuthenticationService;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtService  jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserRequest registerUserDto) {
        User registeredUser = authenticationService.register(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
