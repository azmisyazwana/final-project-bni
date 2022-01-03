package com.finalproject.userservice.service.impl;

import com.finalproject.userservice.dto.input.CreateProfileRequest;
import com.finalproject.userservice.dto.input.UpdateProfileRequest;
import com.finalproject.userservice.dto.output.UpdateProfileResponse;
import com.finalproject.userservice.model.User;
import com.finalproject.userservice.model.UserProfile;
import com.finalproject.userservice.repository.UserProfileRepository;
import com.finalproject.userservice.repository.UserRepository;
import com.finalproject.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserProfile createProfile(Authentication authentication, CreateProfileRequest req) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
//        userProfile.setUserId(user.getId());
        userProfile.setFullname(req.getFullname());
        userProfile.setAddress(req.getAddress());
        userProfile.setDob(req.getDob());
        userProfile.setPhone(req.getPhone());
//        log.info("INI USER ID " + user.getId());
//        user.setEnabled(false);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UpdateProfileResponse updateProfile(Authentication authentication, UpdateProfileRequest req, Long id) throws Exception {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        UpdateProfileResponse updateProfileResponse = new UpdateProfileResponse();
        log.info(id.equals(user.getId()));
        if (id.equals(user.getId())) {
            Optional<UserProfile> userProfile = userProfileRepository.findById(id);
            log.info("masuk sini");
            if (userProfile.isPresent()) {
                UserProfile tempUserProfile = userProfile.get();
//                tempUserProfile.setUser(req.getUser());
//                user.setUserProfile(req.getUserProfile());
                tempUserProfile.setFullname(req.getFullname());
                tempUserProfile.setAddress(req.getAddress());
                tempUserProfile.setDob(req.getDob());
                tempUserProfile.setPhone(req.getPhone());
                user.setUsername(req.getUsername());
                user.setEmail(req.getEmail());
                user.setPassword(passwordEncoder.encode(req.getPassword()));
                userProfileRepository.save(tempUserProfile);
                userRepository.save(user);

                updateProfileResponse.setUser(user);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
            }
        }else{
            log.info("masuk exception");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden to update profile");
        }
        return updateProfileResponse;
    }
}