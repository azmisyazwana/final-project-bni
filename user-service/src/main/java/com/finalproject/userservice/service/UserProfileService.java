package com.finalproject.userservice.service;

import com.finalproject.userservice.dto.input.CreateProfileRequest;
import com.finalproject.userservice.dto.input.UpdateProfileRequest;
import com.finalproject.userservice.dto.output.UpdateProfileResponse;
import com.finalproject.userservice.model.UserProfile;
import org.springframework.security.core.Authentication;

public interface UserProfileService {
    UserProfile createProfile(CreateProfileRequest req);
    UpdateProfileResponse updateProfile(UpdateProfileRequest req, Long id) throws Exception;
}