package com.finalproject.userservice.controller;

import com.finalproject.userservice.dto.output.AuthenticationResponse;
import com.finalproject.userservice.dto.output.TokenResponse;
import com.finalproject.userservice.dto.input.UsernamePassword;
import com.finalproject.userservice.dto.input.RegisterRequest;
import com.finalproject.userservice.dto.output.BaseResponse;
import com.finalproject.userservice.model.User;
import com.finalproject.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<User>> register(@RequestBody RegisterRequest registerRequest){
        User registerUser = authService.register(registerRequest);
        return ResponseEntity.ok(new BaseResponse<>(registerUser));
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UsernamePassword usernamePassword){
        TokenResponse token = authService.generateToken(usernamePassword);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthenticationResponse>> login(@RequestBody UsernamePassword usernamePassword){
        AuthenticationResponse loginUser = authService.login(usernamePassword);
        return ResponseEntity.ok(new BaseResponse<>(loginUser));
    }
}
