package com.finalproject.userservice.service;

import com.finalproject.userservice.dto.input.IdRequest;
import com.finalproject.userservice.dto.output.AccountUserOutput;

import java.util.List;

public interface AdminService {
    List<AccountUserOutput> getAllUser();
    AccountUserOutput getUserById(Long id);
    void deleteById(Long id);
    void activateAccountById(Long id);
    void activateMultipleAccount(List<IdRequest> userActivate);
}
