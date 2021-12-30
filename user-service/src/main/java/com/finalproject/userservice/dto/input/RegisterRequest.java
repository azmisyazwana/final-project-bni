package com.finalproject.userservice.dto.input;

import com.finalproject.userservice.model.GenderType;
import com.finalproject.userservice.model.RoleType;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private RoleType role;
    private GenderType gender;
}
