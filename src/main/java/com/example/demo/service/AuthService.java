package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

   public AuthResponse register(RegisterRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    System.out.println("🔐 Registered user: " + user.getUsername());
    System.out.println("🔐 Hashed password saved in DB: " + user.getPassword());

    userRepository.save(user);

    String token = jwtService.generateToken(user.getUsername()); 
    return new AuthResponse(token);
}


    public AuthResponse login(LoginRequest request) {
    System.out.println("🔑 Login attempt - Username: " + request.getUsername() + ", Password: " + request.getPassword());

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

    System.out.println("✅ Fetched user: " + user.getUsername());
    System.out.println("✅ Hashed password from DB: " + user.getPassword());

    String token = jwtService.generateToken(user.getUsername());
    return new AuthResponse(token);
}

}
