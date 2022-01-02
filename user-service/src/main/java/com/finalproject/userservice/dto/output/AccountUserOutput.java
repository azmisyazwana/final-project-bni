package com.finalproject.userservice.dto.output;

import com.finalproject.userservice.model.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountUserOutput {
    private Long id;
    private String username;
    private String email;
    private GenderType gender;
    private boolean enabled;
}
