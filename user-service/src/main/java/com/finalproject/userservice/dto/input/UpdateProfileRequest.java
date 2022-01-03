package com.finalproject.userservice.dto.input;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateProfileRequest {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private Date dob;
    private String address;
    private String phone;
//    private BaseUser user;
//    private BaseUserProfile userProfile;
}
