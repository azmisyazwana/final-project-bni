package com.finalproject.userservice.dto.input;

import lombok.Data;

import java.util.Date;

@Data
public class CreateProfileRequest {
//    public Long id;
    private String fullname;
    private Date dob;
    private String address;
    private String phone;
}
