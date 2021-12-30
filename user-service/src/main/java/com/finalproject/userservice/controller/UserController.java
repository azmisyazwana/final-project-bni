package com.finalproject.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getName(Principal principal){
        return ResponseEntity.ok(principal.getName());
    }
}