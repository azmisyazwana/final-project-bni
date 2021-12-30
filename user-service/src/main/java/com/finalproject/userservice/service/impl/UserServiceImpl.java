package com.finalproject.userservice.service.impl;

import com.finalproject.userservice.model.User;
import com.finalproject.userservice.repository.UserRepository;
import com.finalproject.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getDistinctTopByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }
}

