package com.myproject.hotelmanagementsystem.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.hotelmanagementsystem.config.JwtService;
import com.myproject.hotelmanagementsystem.entity.Role;
import com.myproject.hotelmanagementsystem.entity.User;
import com.myproject.hotelmanagementsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNo(request.getPhoneNo())
                    .age(request.getAge())
                    .gender(request.getGender())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.GUEST)
                    .build();

        if (user.getEmail().endsWith("admin@gmail.com"))
            user.setRole(Role.ADMIN);
        
        else if (user.getEmail().endsWith("manager@gmail.com"))
            user.setRole(Role.MANAGER);

        else user.setRole(Role.GUEST);

        userRepo.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
