package com.finalproject.userservice.controller;

import com.finalproject.userservice.dto.input.CreateProfileRequest;
import com.finalproject.userservice.dto.input.UpdateProfileRequest;
import com.finalproject.userservice.dto.output.BaseResponse;
import com.finalproject.userservice.dto.output.UpdateProfileResponse;
import com.finalproject.userservice.model.UserProfile;
import com.finalproject.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getName(Principal principal){
        return ResponseEntity.ok(principal.getName());
    }

//    @GetMapping("/info/1")
//    public String getName(Authentication authentication){
//        return authentication.getName() ;
//    }

    @PostMapping
    public UserProfile createUserProfile(Authentication authentication, @RequestBody CreateProfileRequest createProfileRequest){
        return userProfileService.createProfile(authentication, createProfileRequest);
    }

    @PatchMapping("/update-profile/{id}")
    public ResponseEntity<BaseResponse<Boolean>> updateProfile(Authentication authentication, @RequestBody UpdateProfileRequest updateProfileRequest, @PathVariable Long id) throws Exception {
        userProfileService.updateProfile(authentication, updateProfileRequest, id);
        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
    }
}