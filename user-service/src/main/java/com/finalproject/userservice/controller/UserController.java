package com.finalproject.userservice.controller;

import com.finalproject.userservice.dto.input.*;
import com.finalproject.userservice.dto.output.*;
import com.finalproject.userservice.model.User;
import com.finalproject.userservice.model.UserProfile;
import com.finalproject.userservice.service.AdminService;
import com.finalproject.userservice.service.AuthService;
import com.finalproject.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;
    private final AdminService adminService;
    private final AuthService authService;


    @GetMapping
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }

//    @GetMapping("/info")
//    public ResponseEntity<?> getName(Principal principal){
//        return ResponseEntity.ok(principal.getName());
//    }

//    @GetMapping("/info/1")
//    public String getName(Authentication authentication){
//        return authentication.getName() ;
//    }

    @PostMapping
    public UserProfile createUserProfile(@RequestBody CreateProfileRequest createProfileRequest){

        return userProfileService.createProfile(createProfileRequest);
    }

    @PatchMapping("/update-profile/{id}")
    public ResponseEntity<BaseResponse<Boolean>> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest, @PathVariable Long id) throws Exception {
        userProfileService.updateProfile(updateProfileRequest, id);
        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
    }


    @GetMapping("/user-id-for-post/{id}")
    public AccountUserOutput getUserByIdForPost(@PathVariable Long id){
        AccountUserOutput accountUserOutput = adminService.getUserById(id);
        return accountUserOutput;
    }

//    @PostMapping("/auth/register")
//    public ResponseEntity<BaseResponse<User>> register(@RequestBody RegisterRequest registerRequest){
//        User registerUser = authService.register(registerRequest);
//        return ResponseEntity.ok(new BaseResponse<>(registerUser));
//    }
//
//    @PostMapping("/auth/token")
//    public ResponseEntity<?> generateToken(@RequestBody UsernamePassword usernamePassword){
//        TokenResponse token = authService.generateToken(usernamePassword);
//        return ResponseEntity.ok(token);
//    }
//
//    @PostMapping("/auth/login")
//    public ResponseEntity<BaseResponse<AuthenticationResponse>> login(@RequestBody UsernamePassword usernamePassword){
//        AuthenticationResponse loginUser = authService.login(usernamePassword);
//        return ResponseEntity.ok(new BaseResponse<>(loginUser));
//    }
//
//    @GetMapping("/admin")
//    public ResponseEntity<List<AccountUserOutput>> getAllUser(){
//        return ResponseEntity.ok(adminService.getAllUser());
//    }
//
//    @GetMapping("/admin/{id}")
//    public ResponseEntity<BaseResponse<AccountUserOutput>> getUserById(@PathVariable Long id){
//        AccountUserOutput accountUserOutput = adminService.getUserById(id);
//        return ResponseEntity.ok(new BaseResponse<>(accountUserOutput));
//    }
//
//    @DeleteMapping("/admin/{id}")
//    public ResponseEntity<BaseResponse<Boolean>> deleteById(@PathVariable Long id){
//        adminService.deleteById(id);
//        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
//    }
//
//    @PatchMapping("/admin/activate-account/{id}")
//    public ResponseEntity<BaseResponse<Boolean>> activateAccountById(@PathVariable Long id){
//        adminService.activateAccountById(id);
//        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
//    }
//
//    @PatchMapping("/admin/activate-account")
//    public ResponseEntity<BaseResponse<Boolean>> activateAccountById(@RequestBody List<IdRequest> userActivate){
//        adminService.activateMultipleAccount(userActivate);
//        return ResponseEntity.ok(new BaseResponse<>(Boolean.TRUE));
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public BaseResponse<?> handleValidationError(MethodArgumentNotValidException ex){
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldError();
//        String  defaultMessage = fieldError.getDefaultMessage();
//        return new BaseResponse<>(false, defaultMessage);
//    }
}