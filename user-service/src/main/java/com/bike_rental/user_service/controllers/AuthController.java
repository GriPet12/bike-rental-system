package com.bike_rental.user_service.controllers;

import com.bike_rental.user_service.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password){
        String token = authService.loginUser(login, password);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
