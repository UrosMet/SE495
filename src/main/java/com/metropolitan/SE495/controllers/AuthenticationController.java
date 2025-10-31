package com.metropolitan.SE495.controllers;

import com.metropolitan.SE495.authentication.AuthenticationService;
import com.metropolitan.SE495.dto.LoginDTO;
import com.metropolitan.SE495.dto.LoginResponse;
import com.metropolitan.SE495.dto.RegisterDTO;
import com.metropolitan.SE495.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginResponseResponseEntity(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(authenticationService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> registerResponseResponseEntity(@RequestBody RegisterDTO user){
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            authenticationService.logout(token);
            return ResponseEntity.ok(Map.of("message", "Logged out successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Invalid authorization header."));
        }
    }
}
