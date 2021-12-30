package com.finalproject.userservice.service;

import com.finalproject.userservice.dto.output.AuthenticationResponse;
import com.finalproject.userservice.dto.output.TokenResponse;
import com.finalproject.userservice.dto.input.UsernamePassword;
import com.finalproject.userservice.dto.input.RegisterRequest;
import com.finalproject.userservice.model.User;

public interface AuthService {
    User register(RegisterRequest req);
    TokenResponse generateToken(UsernamePassword req);
    AuthenticationResponse login(UsernamePassword req);
}
