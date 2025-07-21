package com.example.user_service.controller;


import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.RegisterDto;
import com.example.user_service.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
