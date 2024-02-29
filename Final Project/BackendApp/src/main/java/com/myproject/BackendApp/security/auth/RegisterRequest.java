package com.myproject.BackendApp.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private  String username;
    private String email;
    private String gender;
    private Integer pinCode;
    private String password;
    private String confirmPassword;

}
