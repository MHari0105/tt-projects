package com.myproject.hotelmanagementsystem.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private Integer age;
    private String gender;
    private String password;
    private String confirmPassword;

}
