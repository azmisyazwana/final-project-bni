package com.finalproject.userservice.controller;

import com.finalproject.userservice.dto.input.IdRequest;
import com.finalproject.userservice.dto.output.AccountUserOutput;
import com.finalproject.userservice.dto.output.BaseResponse;
import com.finalproject.userservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AccountUserOutput>> getAllUser(){
        return ResponseEntity.ok(adminService.getAllUser());
    }

//    @PreAuthorize("ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AccountUserOutput>> getUserById(@PathVariable Long id){
        AccountUserOutput accountUserOutput = adminService.getUserById(id);
        return ResponseEntity.ok(new BaseResponse<>(accountUserOutput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Boolean>> deleteById(@PathVariable Long id){
        adminService.deleteById(id);
        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
    }

    @PatchMapping("/activate-account/{id}")
    public ResponseEntity<BaseResponse<Boolean>> activateAccountById(@PathVariable Long id){
        adminService.activateAccountById(id);
        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
    }

    @PatchMapping("/activate-account")
    public ResponseEntity<BaseResponse<Boolean>> activateAccountById(@RequestBody List<IdRequest> userActivate){
        adminService.activateMultipleAccount(userActivate);
        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
    }
}
