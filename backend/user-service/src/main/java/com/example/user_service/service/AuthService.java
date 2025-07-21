package com.example.user_service.service;


import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
