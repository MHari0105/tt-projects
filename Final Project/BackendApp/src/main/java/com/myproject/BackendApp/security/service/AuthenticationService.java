package com.myproject.BackendApp.security.service;

import com.myproject.BackendApp.security.auth.AuthenticationRequest;
import com.myproject.BackendApp.security.auth.AuthenticationResponse;
import com.myproject.BackendApp.security.auth.RegisterRequest;
import com.myproject.BackendApp.security.repository.UserRepository;
import com.myproject.BackendApp.security.user.Role;
import com.myproject.BackendApp.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .gender(request.getGender())
                .pinCode(request.getPinCode())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PUBLIC)
                .build();

        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        System.out.println(request);
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
