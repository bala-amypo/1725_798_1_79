package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    
    public AuthController(AuthenticationManager authenticationManager, 
                         JwtUtil jwtUtil, 
                         UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {
        // Check if user already exists
        try {
            userService.findByEmail(authRequest.getEmail());
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Email already exists"));
        } catch (Exception e) {
            // User doesn't exist, proceed with registration
        }
        
        User user = User.builder()
                .name(authRequest.getName())
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .role(authRequest.getRole() != null ? authRequest.getRole() : "USER")
                .build();
        
        User registeredUser = userService.register(user);
        
        String token = jwtUtil.generateToken(
            registeredUser.getId(), 
            registeredUser.getEmail(), 
            registeredUser.getRole()
        );
        
        return ResponseEntity.ok(new AuthResponse(token, "Registration successful"));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
                )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            User user = userService.findByEmail(authRequest.getEmail());
            String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
            
            return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Invalid email or password"));
        }
    }
    
    @PostMapping("/validate")
    public ResponseEntity<AuthResponse> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Invalid authorization header"));
        }
        
        String token = authHeader.substring(7);
        try {
            jwtUtil.validateToken(token);
            return ResponseEntity.ok(new AuthResponse(token, "Token is valid"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Invalid token"));
        }
    }
}