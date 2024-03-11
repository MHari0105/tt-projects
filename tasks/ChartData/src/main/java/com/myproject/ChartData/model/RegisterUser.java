package com.myproject.ChartData.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "register_user")
public class RegisterUser {
    
    @Id
    private String id;
    private String username;
    private String email;
    private String location;
    private String password;
    
}
