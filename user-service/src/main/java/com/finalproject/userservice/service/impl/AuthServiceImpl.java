package com.finalproject.userservice.service.impl;

import com.finalproject.userservice.dto.output.AuthenticationResponse;
import com.finalproject.userservice.dto.output.TokenResponse;
import com.finalproject.userservice.dto.input.UsernamePassword;
import com.finalproject.userservice.dto.input.RegisterRequest;
import com.finalproject.userservice.model.RoleType;
import com.finalproject.userservice.model.User;
import com.finalproject.userservice.repository.UserRepository;
import com.finalproject.userservice.security.JwtProvider;
import com.finalproject.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @Override
    public User register(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        if(req.getRole()==null){
            user.setRole(RoleType.USER);
        }else{
            user.setRole(req.getRole());
        }
        user.setGender(req.getGender());
        user.setEnabled(user.isEnabled());
//        user.setEnabled(false);
        return userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(UsernamePassword req){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, req.getUsername());
    }

    @Override
    public TokenResponse generateToken(UsernamePassword req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()
                    )
            );
            log.info(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            TokenResponse response = new TokenResponse();
            response.setToken(jwt);
            return response;
        }catch (BadCredentialsException e){
            log.error("Bad Credential", e);
            throw new RuntimeException(e.getMessage(), e);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
