package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager manager, JwtUtil jwtUtil, UserService userService) {
        this.authManager = manager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {
        userService.register(user);
        return new ApiResponse("Registered");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        return new AuthResponse(jwtUtil.generateToken(req.getEmail()));
    }
}
