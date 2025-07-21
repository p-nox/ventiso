package com.example.user_service.service.impl;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.RegisterDto;
import com.example.user_service.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(LoginDto loginDto) {
        return "";
    }

    @Override
    public String register(RegisterDto registerDto) {
        return "";
    }
}
