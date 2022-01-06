package com.finalproject.userservice.dto.output;

import com.finalproject.userservice.model.User;
import com.finalproject.userservice.model.UserProfile;
import lombok.Data;

@Data
public class UpdateProfileResponse {
    private User user;
    private UserProfile userProfile;
}
