package com.application.Issue.security.service;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.Issue.exception.NotFoundException;
import com.application.Issue.security.auth.AuthenticationRequest;
import com.application.Issue.security.auth.AuthenticationResponse;
import com.application.Issue.security.auth.ForgotPasswordRequest;
import com.application.Issue.security.auth.RegisterRequest;
import com.application.Issue.security.auth.VerificationRequest;
import com.application.Issue.security.repository.UserRepository;
import com.application.Issue.security.user.Role;
import com.application.Issue.security.user.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final TwoFactorAuthenticationService tfaService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .location(request.getLocation().toLowerCase())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .role(Role.PUBLIC)
                .mfaEnabled(request.isMfaEnabled())
                .build();
        
        if (request.isMfaEnabled()) {
            user.setSecret(tfaService.generateNewSecret());
        }

        User savedUser = userRepo.save(user);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Authorities", savedUser.getAuthorities());
        extraClaims.put("location", savedUser.getLocation());
        extraClaims.put("userId", savedUser.getId());

        System.out.println(savedUser.getAuthorities());
        String jwtToken = jwtService.generateToken(extraClaims, savedUser);
        return AuthenticationResponse.builder()
                .secretImageUri(tfaService.generateQrCodeImageUri(savedUser.getSecret()))
                .token(jwtToken)
                .mfaEnabled(savedUser.isMfaEnabled())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getUserName());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getUserPassword()));
        System.out.println(request);
        
        var user = userRepo.findByUserName(request.getUserName()).orElseThrow();

        if (user.isMfaEnabled()) {
            return AuthenticationResponse.builder()
                    .token("")
                    .mfaEnabled(true)
                    .build();
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Authorities", user.getAuthorities());
        extraClaims.put("location", user.getLocation());
        extraClaims.put("userId", user.getId());

        System.out.println(user.getAuthorities());
        String jwtToken = jwtService.generateToken(extraClaims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .mfaEnabled(false)
                .build();
    }

    public void updateUserPassword(ForgotPasswordRequest request) {
        User user = userRepo.findByUserName(request.getUserName())
                             .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!request.getUserPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        userRepo.save(user);
    }

    public AuthenticationResponse verifyCode(VerificationRequest request) {
        User user = userRepo.findByUserName(request.getUserName()).orElseThrow(
            () -> new NotFoundException(String.format("No user found with %S", request.getUserName()))
        );

        if (tfaService.isOtpNotValid(user.getSecret(), request.getCode())) {
            throw new BadCredentialsException("Code is not correct");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
    
}